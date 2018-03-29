package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StudentRepository extends Repository<Student, Long>, JpaRepository<Student, Long> {

    Student findByRegistrationNumber(String registrationNumber);

    List<Student> findStudentsByCoursesContains(Course course);

}
