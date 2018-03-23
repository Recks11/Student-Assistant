package com.emmanuelirem.studentassistant.models.university;

import com.emmanuelirem.studentassistant.models.Course;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Entity
public class Program {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
        @JoinColumn(name = "department_id")
        private Department department;

        @NaturalId
        private String name;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "program_courses",
                joinColumns = @JoinColumn(name = "program_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id")
        )
        private Set<Course> courses = new HashSet<>();

        public Program() {
        }


        public Program(String name) {
                this.name = name;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public Department getDepartment() {
                return department;
        }

        public void setDepartment(Department department) {
                this.department = department;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Set<Course> getCourses() {
                return courses;
        }

        public void setCourses(Set<Course> courses) {
                this.courses = courses;
        }

        public void addCourse(Course course) {
                if (courses == null) {
                        courses = new HashSet<>();
                }

                courses.add(course);
                course.addProgram(this);

        }

        @Override
        public String toString() {
                return "Program{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
        }
}
