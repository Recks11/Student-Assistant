package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.LecturerRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LecturerServiceImpl implements LecturerService{

    private LecturerRepository lecturerRepository;
    private CourseService courseService;
    private UsersService usersService;
    private final EncoderService encoderService;


    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseService courseService, UsersService usersService, EncoderService encoderService) {
        this.lecturerRepository = lecturerRepository;
        this.courseService = courseService;
        this.usersService = usersService;
        this.encoderService = encoderService;
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
        lecturer.setSchoolEmailAddress(lecturer.getFirstName()+"."+lecturer.getLastName()+"@covenantuniversity.edu.ng");
        return lecturerRepository.save(lecturer).map(newLecturer -> {
            Users user = new Users();
            user.setUsername(lecturer.getUsername().toLowerCase());
            user.setPassword(encoderService.passwordEncoder().encode(lecturer.getPassword()));
            user.setEnabled(true);
            user.setLocked(false);
            user.setAuthorities(new String[]{"ROLE_LECTURER"});
            user.setExpired(false);
            user.setCredentialNotExpired(true);
            usersService.save(user).subscribe();
            return newLecturer;
        });
    }

    @Override
    public Mono<Lecturer> update(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Mono<Lecturer> addCourseToLecturer(Lecturer lecturer, Course course) {
        if(course!= null && !lecturer.getCourses().contains(course)){
            lecturer.addCourse(course);
            return this.update(lecturer);
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<Lecturer> removeCourseFromLecturer(Lecturer lecturer, Course course) {
        if(course != null && lecturer.getCourses().contains(course)){
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
