package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.CourseRepository;
import com.emmanuelirem.studentassistant.repository.LecturerRepository;
import com.emmanuelirem.studentassistant.services.data.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final EncoderService encoderService;
    private final RegexService regexService;
    private final LecturerRepository lecturerRepository;
    private final CourseService courseService;
    private final UsersService usersService;


    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseService courseService,
                               UsersService usersService, EncoderService encoderService,
                               RegexService regexService) {
        this.lecturerRepository = lecturerRepository;
        this.courseService = courseService;
        this.usersService = usersService;
        this.encoderService = encoderService;
        this.regexService = regexService;
    }


    @Override
    public Mono<Lecturer> fromStudent(Student student) {
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(student.getFirstName());
        lecturer.setLastName(student.getLastName());
        lecturer.setUsername(student.getRegistrationNumber());
        lecturer.setPassword(student.getPassword());
        return Mono.just(lecturer);
    }

    @Override
    public Mono<Lecturer> save(Lecturer lecturer) {

        if (regexService.matchesLecturerId(lecturer.getUsername())) {
            lecturer.setSchoolEmailAddress(lecturer.getFirstName() + "." + lecturer.getLastName() + "@covenantuniversity.edu.ng");
            Users user = new Users();
            user.setUsername(lecturer.getUsername().toLowerCase());
            user.setPassword(encoderService.passwordEncoder().encode(lecturer.getPassword()));
            user.setEnabled(true);
            user.setLocked(false);
            user.setAuthorities(new String[]{"ROLE_LECTURER"});
            user.setExpired(false);
            user.setCredentialNotExpired(true);
            lecturer.setUsername(lecturer.getUsername().toLowerCase());

            return lecturerRepository.save(lecturer)
                    .then(usersService.save(user))
                    .thenReturn(lecturer)
                    .doOnError(Mono::error);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Mono<Lecturer> update(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Flux<Lecturer> getAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Mono<Lecturer> getLecturerById(String id) {
        return lecturerRepository.findById(id);
    }

    @Override
    public Mono<Lecturer> addCourseToLecturer(String lecturerId, Course course) {
        //ONLY JESUS KNOWS WHY THIS CODE BREAKS THE LECTURER END POINT
        //I AM VERY CONFUSED AND SAD
        //IT WORKS BUT THE `/lecturer` END POINT JUST BREAKS. COMPLETELY
        //FIXED!!: Saving courses with a list instead of a single object fixed the problem weird

        List<Course> courses = new ArrayList<>();
        return this.getLecturerById(lecturerId).log()
                .map((lecturer) -> {
                    lecturer.addCourse(course);
                    courses.add(course);
                    return lecturer;
                })
                .flatMap(this::update)
                .thenMany(courseService.saveAll(Flux.fromIterable(courses)))
                .then(this.getLecturerById(lecturerId));
    }

    @Override
    public Mono<Lecturer> removeCourseFromLecturer(String lecturerId, String courseId) {
        List<Course> courses = new ArrayList<>();
        return lecturerRepository.findById(lecturerId)
                .flatMap(lecturer -> courseService.findCourseById(courseId)
                        .flatMap(course -> {
                            lecturer.removeCourse(course);
                            courses.add(course);
                            return this.update(lecturer);
                        }))
                .thenMany(courseService.saveAll(Flux.fromIterable(courses)))
                .then(this.getLecturerById(lecturerId));
    }

    @Override
    public Flux<Course> getCoursesWithLecturer(Lecturer lecturer) {
        return courseService.findCoursesWithLecturer(lecturer);
    }

    @Override
    public Mono<Lecturer> getLecturerByIdentifier(String name) {
        return lecturerRepository.findLecturerByUsername(name);
    }

    @Override
    public Mono<Lecturer> getLecturerFromRequest(WebRequest request) {
        return lecturerRepository.findLecturerByUsername(request.getUserPrincipal().getName());
    }
}
