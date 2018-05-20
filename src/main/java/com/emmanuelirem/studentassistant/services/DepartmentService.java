package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.university.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {

    Flux<Department> findAll();
    Mono<Department> findDepartmentById(String id);
}
