package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.Program;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProgramRepository extends ReactiveMongoRepository<Program, String> {

    Flux<Program> findProgramsByDepartment_Name(String name);
    Mono<Program> findProgramByName(String name);
}
