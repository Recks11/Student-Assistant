package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

    Mono<Student> findByRegistrationNumber(String registrationNumber);

    Flux<Student> findStudentsByCoursesContains(Course course);

}
