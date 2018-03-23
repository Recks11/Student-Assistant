package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long>, JpaRepository<Student, Long> {

    Student findByRegistrationNumber(String registrationNumber);
}
