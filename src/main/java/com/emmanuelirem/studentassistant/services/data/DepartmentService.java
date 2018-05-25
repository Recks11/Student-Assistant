package com.emmanuelirem.studentassistant.services.data;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.models.v2Mapper.DepartmentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {

    Flux<Department> findAll();
    Mono<Department> findDepartmentById(String id);
    Flux<Program> getPrograms(String id);

    Flux<DepartmentDTO> findAllMinimised();
    Flux<Reference> getProgramsMinimised(String id);
    Mono<DepartmentDTO> findMinimizedDepartmentById(String id);
}
