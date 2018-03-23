package com.emmanuelirem.studentassistant.repository;


import com.emmanuelirem.studentassistant.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
}
