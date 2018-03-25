package com.emmanuelirem.studentassistant.models.university;

import com.emmanuelirem.studentassistant.models.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Program {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
        @JoinColumn(name = "department_id")
        private Department department;

        private String name;

        @ManyToMany
        @JoinTable(
                name = "program_courses",
                joinColumns = @JoinColumn(name = "program_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id")
        )
        private List<Course> courses = new ArrayList<>();

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

        public List<Course> getCourses() {
                return courses;
        }

        public void setCourses(List<Course> courses) {
                this.courses = courses;
        }

        public void addCourse(Course course) {
                if (courses == null) {
                        courses = new ArrayList<>();
                }

                if (courses.contains(course))
                        return;
                courses.add(course);
                course.addProgram(this);

        }
        public void removeCourse(Course course){
                if(courses.contains(course))
                        courses.remove(course);
                if(course.getPrograms().contains(this))
                        course.getPrograms().remove(this);
        }

        public void addCourses(List<Course> coursesList){
                coursesList.forEach(this::addCourse);
        }

        @Override
        public String toString() {
                return "Program{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
        }
}
