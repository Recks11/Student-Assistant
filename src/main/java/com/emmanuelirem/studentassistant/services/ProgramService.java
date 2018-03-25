package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.university.Program;

import java.util.List;

public interface ProgramService {

    List<Program> findProgramsByDepartment_Name(String name);
    Program findProgramByName(String name);
}
