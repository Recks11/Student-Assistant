package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long>{
}
