package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.ProgramRepository;
import com.emmanuelirem.studentassistant.services.data.ProgramService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService{

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Flux<Program> findProgramsByDepartment_Name(String name) {
        return programRepository.findProgramsByDepartment_Name(name);
    }

    @Override
    public Mono<Program> findProgramByName(String name) {
        return programRepository.findProgramByName(name);
    }

    @Override
    public Flux<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Mono<Program> getById(String id) {
        return programRepository.findById(id);
    }
}
