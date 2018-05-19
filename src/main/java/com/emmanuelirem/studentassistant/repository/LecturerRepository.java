package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.Lecturer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LecturerRepository extends ReactiveMongoRepository<Lecturer, String> {

    Mono<Lecturer> findLecturerByUsername(String name);
}
