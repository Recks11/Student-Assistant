package com.emmanuelirem.studentassistant.Repository;

import com.emmanuelirem.studentassistant.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
