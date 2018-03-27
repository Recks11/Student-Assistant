package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.helper.MatchesIdPattern;
import com.emmanuelirem.studentassistant.models.university.Program;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    @NotBlank(message = "Registration number must be entered")
    @Size(min=6,max=10,message = "Registration number not valid")
    @MatchesIdPattern
    private String registrationNumber;
    private String hallOfResidence;
    private String roomNumber;
    private String password;

    @ManyToOne
    @JoinTable(
            name = "student_program",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private Program program;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String registrationNumber, String hallOfResidence, String roomNumber, String password, Program program, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.hallOfResidence = hallOfResidence;
        this.roomNumber = roomNumber;
        this.password = password;
        this.program = program;
        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getHallOfResidence() {
        return hallOfResidence;
    }

    public void setHallOfResidence(String hallOfResidence) {
        this.hallOfResidence = hallOfResidence;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Course> getCourses() {
        return courses;
}

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if(courses == null) {
            courses = new ArrayList<>();
        }
        if(!courses.contains(course)){
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void removeCourse(Course course){
        if (course.getStudents().contains(this))
            course.getStudents().remove(this);

        if(courses.contains(course)){
            courses.remove(course);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message){
        if(messages.contains(message))
            messages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", hallOfResidence='" + hallOfResidence + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
