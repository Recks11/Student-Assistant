package com.emmanuelirem.studentassistant.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String schoolEmailAddress;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "lecturer_course",
            joinColumns=@JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    public Lecturer() {
    }

    public Lecturer(String firstName, String lastName, String schoolEmailAddress, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolEmailAddress = schoolEmailAddress;
        this.username = username;
        this.password = password;
    }

    public Lecturer(String firstName, String lastName, String schoolEmailAddress, String username, String password, Set<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolEmailAddress = schoolEmailAddress;
        this.username = username;
        this.password = password;
        this.setCourses(courses);
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

    public String getSchoolEmailAddress() {
        return schoolEmailAddress;
    }

    public void setSchoolEmailAddress(String schoolEmailAddress) {
        this.schoolEmailAddress = schoolEmailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        if(!courses.isEmpty()){
            courses.forEach(this::addCourse);
        }
    }

    public void addCourse(Course course) {
        if(courses == null) {
            courses = new HashSet<>();
        }

        if(!courses.contains(course)){
            courses.add(course);
            course.addLecturer(this);
        }
    }

    public void removeCourse(Course course) {
            if(courses.contains(course)){
                courses.remove(course);
            }
            if(course.getLecturers().contains(this)){
                course.removeLecturer(this);
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
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schoolEmailAddress='" + schoolEmailAddress + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses +
                '}';
    }
}
