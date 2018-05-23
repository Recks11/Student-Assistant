package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Program;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseService {

    Mono<Course> saveOrUpdate(Course course);

    Mono<Course> findCourseById(String id);

    Flux<Course> saveAll(Flux<Course> courseFlux);

    Mono<Course> findCourseContainingLecturer(Lecturer lecturer);

    Flux<Course> findCoursesWithLecturer(Lecturer lecturer);

    Flux<Course> findCoursesByIds(List<String> idList);

    Mono<Course> setOrUpdateCoursePassword(Course course, String password);

    Flux<Course> findAll();
}
