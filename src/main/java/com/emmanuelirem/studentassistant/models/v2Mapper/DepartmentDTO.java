package com.emmanuelirem.studentassistant.models.v2Mapper;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDTO extends Department {

    @JsonProperty("programs")
    private List<Reference<Program>> programsReference = new ArrayList<>();


    public List<Reference<Program>> getProgramsReference() {
        return programsReference;
    }

    public void setProgramsReference(List<Reference<Program>> programsReference) {
        this.programsReference = programsReference;
    }

    public void addReference(Reference<Program> program) {
        if(!this.programsReference.contains(program))
            this.programsReference.add(program);
    }

    public void removeReference(Reference<Program> program) {
        this.programsReference.remove(program);
    }
}
