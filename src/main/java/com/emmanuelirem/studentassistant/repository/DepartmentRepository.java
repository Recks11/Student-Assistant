package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {

    Mono<Department> findDepartmentByName(String name);
    Flux<Department> findDepartmentsByCollege_Id(String id);
}
