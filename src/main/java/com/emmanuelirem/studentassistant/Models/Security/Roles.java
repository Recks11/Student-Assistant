package com.emmanuelirem.studentassistant.Models.Security;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "registration_number")
    private String registrationNumber;
    private String role;

    public Roles() {
    }

    public Roles(String registrationNumber, String role) {
        this.registrationNumber = registrationNumber;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
