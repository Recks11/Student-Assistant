package com.emmanuelirem.studentassistant.models.university;

import com.emmanuelirem.studentassistant.models.enums.CollegeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class College {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    public College() {
    }

    public College(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        College college = (College) o;

        return id == college.id;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
