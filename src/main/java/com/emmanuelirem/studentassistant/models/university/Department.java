package com.emmanuelirem.studentassistant.models.university;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Department {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @DBRef
    private College college;

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

    private boolean sameAsFormer(College newCollege) {
        return college == null ? newCollege == null : college.equals(newCollege);
    }


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
