package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.university.Program;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProgramService {

    Flux<Program> findProgramsByDepartment_Name(String name);
    Mono<Program> findProgramByName(String name);
    Flux<Program> findAll();
    Mono<Program> getById(String id);
}
