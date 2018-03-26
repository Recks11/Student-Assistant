package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.enums.SemesterEnum;
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
    private String password;

    @Enumerated(EnumType.STRING)// This line tells Hibernate that this enumeration should be saved in the database as a string
    private SemesterEnum semester;

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




    public Course(int units, int level, String code, String title, boolean compulsory, String password, SemesterEnum semester) {
        this.units = units;
        this.level = level;
        this.code = code;
        this.title = title;
        this.compulsory = compulsory;
        this.password = password;
        this.semester = semester;
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
        if (!lecturer.getCourses().contains(this)) {
            lecturer.addCourse(this);
        }
        if(!lecturers.contains(lecturer)){
            lecturers.add(lecturer);
        }
    }

    public void removeLecturer(Lecturer lecturer){
        if(lecturers.contains(lecturer)){
            lecturers.remove(lecturer);
        }
        if(lecturer.getCourses().contains(this))
            lecturer.removeCourse(this);
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
        if(!students.contains(student)) {
            students.add(student);
            student.getCourses().add(this);
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", units=" + units +
                ", level=" + level +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", compulsory=" + compulsory +
                ", password='" + password + '\'' +
                ", semester=" + semester +
                '}';
    }
}
