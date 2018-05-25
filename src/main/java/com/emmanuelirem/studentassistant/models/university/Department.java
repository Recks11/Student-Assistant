package com.emmanuelirem.studentassistant.models.university;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Department extends Reference {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @DBRef
    private College college;

    @DBRef
    @JsonIgnore
    private List<Program> programs = new ArrayList<>();


    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        if (sameAsFormer(college)) {
            return;
        }
        this.college = college;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public void addProgram(Program program) {
        if(!this.programs.contains(program))
            this.programs.add(program);
    }

    public void removeProgram(Program program) {
        this.programs.remove(program);
    }

    private boolean sameAsFormer(College newCollege) {
        return college == null ? newCollege == null : college.equals(newCollege);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!id.equals(that.id)) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
