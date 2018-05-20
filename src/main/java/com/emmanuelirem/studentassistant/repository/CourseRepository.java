package com.emmanuelirem.studentassistant.repository;


import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Program;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String>{

    Flux<Course> findCoursesByLecturersContains(Lecturer lecturer);

    Mono<Course> findCourseByLecturersContains(Lecturer lecturer);
}
