package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import com.emmanuelirem.studentassistant.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final CourseService courseService;
    private final ProgramService programService;
    private final StudentRepository studentRepository;
    private final EncoderService encoderService;
    private final UsersService usersService;
    private final RegexService regexService;

    public StudentServiceImpl(CourseService courseService, ProgramService programService, StudentRepository studentRepository, EncoderService encoderService, UsersService usersService, RegexService regexService) {
        this.courseService = courseService;
        this.programService = programService;
        this.studentRepository = studentRepository;
        this.encoderService = encoderService;
        this.usersService = usersService;
        this.regexService = regexService;
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
        student.setEmailAddress(student.getFirstName().toLowerCase() + "." + student.getLastName().toLowerCase() + "@stu.cu.edu.ng");
        if (regexService.matchesStudentRegNumber(student.getRegistrationNumber())) {
            Users user = new Users();
            user.setUsername(student.getRegistrationNumber().toLowerCase());
            user.setPassword(encoderService.passwordEncoder().encode(student.getPassword()));
            user.setEnabled(true);
            user.setLocked(false);
            user.setAuthorities(new String[]{"ROLE_STUDENT"});
            user.setExpired(false);
            user.setCredentialNotExpired(true);

            usersService.save(user).subscribe();
            return studentRepository.save(student)
                    .then(usersService.save(user)
                            .thenReturn(student))
                    .doOnError(Mono::error);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Mono<Student> update(Student student) {
        return studentRepository.save(student);
    }

    public Flux<Student> saveAll(List<Student> studentList) {
        return studentRepository.saveAll(studentList);
    }

    @Override
    public Mono<Student> registerCourse(Course course, Student student) {
        student.addCourse(course);
        return this.update(student);
    }

    @Override
    public Mono<Student> registerCourses(List<Course> courses, Student student) {
        courses.forEach(student::addCourse);
        return this.update(student);
    }

    @Override
    public Mono<Student> removeCourse(Student student, Course course) {
        if (course != null)
            student.removeCourse(course);
        return this.update(student);
    }

    @Override
    public Flux<Course> findUnregisteredCoursesForStudent(String id, String programId) {

        return studentRepository.findById(id)
                .flatMapMany(student -> programService.getById(programId)
                        .flatMapMany(program -> Flux.fromIterable(program.getCourses()))
                        .filter(course -> !student.getCourses().contains(course)));
    }
}
