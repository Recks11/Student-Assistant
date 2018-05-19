package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.StudentService;
import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private final CourseService courseService;
    private final StudentRepository studentRepository;
    private final EncoderService encoderService;
    private final UsersService usersService;

    public StudentServiceImpl(CourseService courseService, StudentRepository studentRepository, UsersService usersService, EncoderService encoderService) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
        this.encoderService = encoderService;
        this.usersService = usersService;
    }

    @Override
    public Mono<Student> findByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Mono<Student> getLoggedInStudentFromRequest(WebRequest request) {
        return studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());
    }

    @Override
    public Flux<Student> findStudentsOfferingCourse(Course course) {
        return studentRepository.findStudentsByCoursesContains(course);
    }
    @Override
    public Mono<Student> save(Student student) {
        student.setEmailAddress(student.getFirstName().toLowerCase()+"."+student.getLastName().toLowerCase()+"@stu.cu.edu.ng");
        return studentRepository.save(student).map(savedStudent -> {
            Users user = new Users();
            user.setUsername(student.getRegistrationNumber().toLowerCase());
            user.setPassword(encoderService.passwordEncoder().encode(savedStudent.getPassword()));
            user.setEnabled(true);
            user.setLocked(false);
            user.setAuthorities(new String[]{"ROLE_STUDENT"});
            user.setExpired(false);
            user.setCredentialNotExpired(true);

            usersService.save(user).subscribe();

            return savedStudent;
        });
    }

    public Mono<Student> update(Student student){
        return studentRepository.save(student);
    }

    public Flux<Student> saveAll(List<Student> studentList){
        return studentRepository.saveAll(studentList);
    }

    @Override
    public Mono<Student> registerCourse(Course course, Student student) {
        student.addCourse(course);
        return this.update(student);
    }

    @Override
    public Mono<Student> registerCourses(List<Course> courses, Student student){
        courses.forEach(student::addCourse);
        return this.update(student);
    }

    @Override
    public Mono<Student> removeCourse(Student student, Course course) {
        if (course!= null)
            student.removeCourse(course);
        return this.update(student);
    }

    @Override
    public Flux<Course> findUnregisteredCoursesForStudent(Student student) {
        Program program = student.getProgram();
        Flux<Course> listOfProgramCourses = courseService.findCoursesContainingProgram(program);
        List<Course> listOfRegisteredCourses = student.getCourses();
        List<Course> listOfCoursesToBeRegistered = new ArrayList<>();

        listOfProgramCourses.toStream().forEach(course -> {
            if (!listOfRegisteredCourses.contains(course)) {
                listOfCoursesToBeRegistered.add(course);
            }
        });
        return Flux.fromStream(listOfCoursesToBeRegistered.stream());
    }
}
