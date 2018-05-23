package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import com.emmanuelirem.studentassistant.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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
        if (regexService.matchesStudentRegNumber(student.getRegistrationNumber())) {
            student.setEmailAddress(student.getFirstName().toLowerCase() + "." + student.getLastName().toLowerCase() + "@stu.cu.edu.ng");
            student.setRegistrationNumber(student.getRegistrationNumber().toLowerCase());

            Users user = new Users();
            user.setUsername(student.getRegistrationNumber().toLowerCase());
            user.setPassword(encoderService.passwordEncoder().encode(student.getPassword()));
            user.setEnabled(true);
            user.setLocked(false);
            user.setAuthorities(new String[]{"ROLE_STUDENT"});
            user.setExpired(false);
            user.setCredentialNotExpired(true);

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
    public Flux<Course> registerCourses(List<Course> courses, Student student) {
        List<String> courseIdList = new ArrayList<>();
        courses.forEach(course -> courseIdList.add(course.getId()));
        List<Course> courseList = new ArrayList<>();

        return courseService.findCoursesByIds(courseIdList)
                .map((course) -> {
                    student.addCourse(course);
                    courseList.add(course);
                    return course;
                })
                .thenMany(courseService.saveAll(Flux.fromIterable(courseList)))
                .then(this.update(student))
                .flatMapIterable(Student::getCourses);
    }

    @Override
    public Mono<Student> removeCourse(Student student, Course course) {
        if (student != null) {
            if (course != null) {
                student.removeCourse(course);
            }
        }

        assert student != null;
        return courseService.saveOrUpdate(course)
                .then(studentRepository.save(student));
    }

    @Override
    public Flux<Course> findUnregisteredCoursesForStudent(String id, String programId) {

        return studentRepository.findById(id)
                .flatMapMany(student -> programService.getById(programId)
                        .flatMapMany(program -> Flux.fromIterable(program.getCourses()))
                        .filter(course -> !student.getCourses().contains(course)));
    }

    @Override
    public Flux<Course> removeCourse(String studentId, String courseId) {
        Mono<Student> studentMono = this.findById(studentId);
        Mono<Course> courseMono = courseService.findCourseById(courseId);

        return courseMono.zipWith(studentMono)
                .flatMapMany(objects -> this.removeCourse(objects.getT2(), objects.getT1()))
                .flatMapIterable(Student::getCourses);
    }
}
