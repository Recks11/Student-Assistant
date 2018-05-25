package com.emmanuelirem.studentassistant.controllers.Api.V2;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.v2Mapper.DepartmentDTO;
import com.emmanuelirem.studentassistant.services.data.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/department")
public class V2DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public V2DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<DepartmentDTO> getAll(){
        return departmentService.findAllMinimised();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<DepartmentDTO> getById(@PathVariable String id) {
        return departmentService.findMinimizedDepartmentById(id);
    }

    @GetMapping("/{id}/programs")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Reference> getPrograms(@PathVariable String id) {
        return departmentService.getProgramsMinimised(id);
    }
}