package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.helper.MatchesIdPattern;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Lecturer {

    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    @JsonProperty("email")
    private String schoolEmailAddress;
    private String office;

    @JsonProperty("phoneNumbers")
    private ArrayList<String> personalPhoneNumber;

    @MatchesIdPattern
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @DBRef
    private List<Department> departments = new ArrayList<>();

    @DBRef
    private List<Course> courses = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Message> messages = new ArrayList<>();

    private boolean inOffice = false;

    public Lecturer() {
    }

    public Lecturer(String firstName, String lastName, String username, String password, String office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.office = office;
    }

    public Lecturer(String firstName, String lastName, String username, String password, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.courses.addAll(courses);
    }

    public Lecturer(String firstName, String lastName, ArrayList<String> personalPhoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalPhoneNumber = personalPhoneNumber;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Course> getCourses() {
        return courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department){
        if(!this.departments.contains(department))
            departments.add(department);
    }

    public void removeDepartment(Department department){
        if(this.departments.contains(department))
            departments.remove(department);
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void addCourse(Course course) {
        if(courses == null) {
            courses = new ArrayList<>();
        }

        if(!courses.contains(course)){
            courses.add(course);
            course.addLecturer(this);
        }
    }

    public void removeCourse(Course course) {
            courses.remove(course);
            course.removeLecturer(this);
    }

    public void setCourses(List<Course> courses) {
        if(!courses.isEmpty()){
            courses.forEach(this::addCourse);
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
            messages.remove(message);
    }

    public ArrayList<String> getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public void setPersonalPhoneNumber(ArrayList<String> personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public void addPhone(String number) {
        if(!personalPhoneNumber.contains(number))
            personalPhoneNumber.add(number);
    }

    public boolean isInOffice() {
        return inOffice;
    }

    public void setInOffice(boolean inOffice) {
        this.inOffice = inOffice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecturer lecturer = (Lecturer) o;

        if (!id.equals(lecturer.id)) return false;
        if (firstName != null ? !firstName.equals(lecturer.firstName) : lecturer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(lecturer.lastName) : lecturer.lastName != null) return false;
        return username.equals(lecturer.username);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schoolEmailAddress='" + schoolEmailAddress + '\'' +
                ", office='" + office + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
