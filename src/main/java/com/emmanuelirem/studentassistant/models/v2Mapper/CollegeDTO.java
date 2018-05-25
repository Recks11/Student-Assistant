package com.emmanuelirem.studentassistant.models.v2Mapper;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CollegeDTO extends College {

    @JsonProperty("departments")
    private List<Reference<Department>> departmentReference = new ArrayList<>();

    public List<Reference<Department>> getDepartmentReference() {
        return departmentReference;
    }

    public void setDepartmentReference(List<Reference<Department>> departmentReference) {
        this.departmentReference = departmentReference;
    }

    public void addReference(Reference<Department> program) {
        if(!this.departmentReference.contains(program))
            this.departmentReference.add(program);
    }

    public void removeReference(Reference<Department> program) {
        this.departmentReference.remove(program);
    }
}
