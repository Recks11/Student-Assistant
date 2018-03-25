package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.ProgramRepository;
import com.emmanuelirem.studentassistant.services.ProgramService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService{

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> findProgramsByDepartment_Name(String name) {
        return programRepository.findProgramsByDepartment_Name(name);
    }

    @Override
    public Program findProgramByName(String name) {
        return programRepository.findProgramByName(name);
    }
}
