package com.emmanuelirem.studentassistant.models.university;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private CollegeEnum name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "college", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Department> departments = new ArrayList<>();

    public College() {
    }

    public College(CollegeEnum name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CollegeEnum getName() {
        return name;
    }

    public void setName(CollegeEnum name) {
        this.name = name;
    }

    public List<Department> getDepartment() {
        return new ArrayList<>(departments);
    }


    public void addDepartment(Department department) {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        departments.add(department);
        department.setCollege(this);
    }

    public void removeDepartment(Department department){

        departments.remove(department);
        department.setCollege(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        College college = (College) o;

        return id == college.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
