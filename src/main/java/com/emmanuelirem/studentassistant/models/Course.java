package com.emmanuelirem.studentassistant.models;

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

    private int units;
    private int level;
    private String code;
    private String title;
    private boolean compulsory;

    @ManyToMany(mappedBy = "courses")
    private List<Program> programs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "lecturer_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns=@JoinColumn(name = "lecturer_id")
    )
    private Set<Lecturer> lecturers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(int units, int level, String code, String title, boolean compulsory) {
        this.units = units;
        this.level = level;
        this.code = code;
        this.title = title;
        this.compulsory = compulsory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
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
        if(programs.contains(program))
            return;
        programs.add(program);
    }

    public void removeProgram(Program program){
        if(program.getCourses().contains(this))
            program.getCourses().remove(this);

        if(programs.contains(program))
            programs.remove(program);
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void addLecturer(Lecturer lecturer) {
        if(lecturers == null) {
            lecturers = new HashSet<>();
        }
        lecturer.getCourses().add(this);
        lecturers.add(lecturer);
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if(students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudent(Student student){
        if(students.contains(student))
            students.remove(student);

        if(student.getCourses().contains(this))
            student.getCourses().remove(this);
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", units=" + units +
                ", level=" + level +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", compulsory='" + compulsory + '\'' +
                '}';
    }
}
