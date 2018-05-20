package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.LecturerRepository;
import com.emmanuelirem.studentassistant.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LecturerServiceImpl implements LecturerService {

    private final EncoderService encoderService;
    private final RegexService regexService;
    private LecturerRepository lecturerRepository;
    private CourseService courseService;
    private UsersService usersService;


    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseService courseService, UsersService usersService, EncoderService encoderService, RegexService regexService) {
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
            usersService.save(user).subscribe();

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
    public Mono<Lecturer> addCourseToLecturer(Lecturer lecturer, Course course) {
        if (course != null && !lecturer.getCourses().contains(course)) {
            lecturer.addCourse(course);
            return this.update(lecturer);
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<Lecturer> removeCourseFromLecturer(Lecturer lecturer, Course course) {
        if (course != null && lecturer.getCourses().contains(course)) {
            lecturer.removeCourse(course);
            return this.update(lecturer);
        } else {
            return Mono.empty();
        }
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
