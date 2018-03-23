package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long>, Repository<Program, Long> {

    List<Program> findProgramsByDepartment_Name(String name);
    Program findProgramByName(String name);
}
