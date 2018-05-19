package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LecturerService {

    Mono<Lecturer> fromStudent(Student student);

    Mono<Lecturer> save(Lecturer lecturer);

    Mono<Lecturer> update(Lecturer lecturer);

    Mono<Lecturer> addCourseToLecturer(Lecturer lecturer, Course course);

    Mono<Lecturer> removeCourseFromLecturer(Lecturer lecturer, Course course);

    Flux<Course> getCoursesWithLecturer(Lecturer lecturer);

    Mono<Lecturer>  getLecturerByIdentifier(String name);

    Mono<Lecturer> getLecturerFromRequest(WebRequest request);
}
