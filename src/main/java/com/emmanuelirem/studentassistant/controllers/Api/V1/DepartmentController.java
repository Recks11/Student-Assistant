package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.data.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Department> getAll(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Department> getById(@PathVariable String id) {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("/{id}/programs")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Program> getPrograms(@PathVariable String id) {
        return departmentService.getPrograms(id);
    }
}
