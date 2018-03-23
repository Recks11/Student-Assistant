package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int level;
    private String code;
    private String title;

    @ManyToMany
    @JoinTable(
            name = "program_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<Program> programs;

    @ManyToMany
    @JoinTable(
            name = "course_lecturer",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns=@JoinColumn(name = "lecturer_id")
    )
    private List<Lecturer> lecturers;

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private Set<Student> students;

    public Course() {
    }

    public Course(int level, String code, String title) {
        this.level = level;
        this.code = code;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public void addProgram(Program program) {
        if(programs==null) {
            programs = new ArrayList<>();
        }
        programs.add(program);
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void addLecturer(Lecturer lecturer) {
        if(lecturers == null) {
            lecturers = new ArrayList<>();
        }
        lecturer.getCourses().add(this);
        lecturers.add(lecturer);
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if(students == null) {
            students = new HashSet<>();
        }

        students.add(student);
        student.getCourses().add(this);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", level=" + level +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
