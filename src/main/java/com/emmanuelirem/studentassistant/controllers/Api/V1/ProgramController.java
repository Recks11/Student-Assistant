package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.data.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramController {


    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Flux<Program> getPrograms() {
        return programService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Program> getProgram(@PathVariable String id) {
        return programService.getById(id);
    }

    //TODO addCourse
}
