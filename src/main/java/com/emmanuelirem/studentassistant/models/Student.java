package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.helper.MatchesIdPattern;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Student {

    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    @JsonProperty("email")
    private String emailAddress;

    @NotBlank(message = "Please fill in this field 😀")
    @Size(min=6,max=10,message = "ID not the right length 😒")
    @MatchesIdPattern
    @JsonProperty("username")
    private String registrationNumber;
    @JsonProperty("hall")
    private String hallOfResidence;
    private String roomNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Min(value = 5, message = "your password isn't good")
    private String password;

    @DBRef
    private Program program;

    @DBRef(lazy = true)
    private List<Course> courses = new ArrayList<>();
    @DBRef(lazy = true)
    @JsonIgnore
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

    public boolean containsAnyCourseInList(List<Course> courses) {
        final boolean[] contained = {false};
        courses.forEach(course -> {
            if (this.courses.contains(course))
                contained[0] = true;
        });
        return contained[0];
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
        if(courses.contains(course)){
            courses.remove(course);
            course.removeStudent(this);
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

        if (!id.equals(student.id)) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(student.emailAddress) : student.emailAddress != null)
            return false;
        if (registrationNumber != null ? !registrationNumber.equals(student.registrationNumber) : student.registrationNumber != null)
            return false;
        if (hallOfResidence != null ? !hallOfResidence.equals(student.hallOfResidence) : student.hallOfResidence != null)
            return false;
        if (roomNumber != null ? !roomNumber.equals(student.roomNumber) : student.roomNumber != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        return program != null ? program.equals(student.program) : student.program == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (hallOfResidence != null ? hallOfResidence.hashCode() : 0);
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (program != null ? program.hashCode() : 0);
        return result;
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
