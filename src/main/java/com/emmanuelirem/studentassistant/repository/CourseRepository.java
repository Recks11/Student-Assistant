package com.emmanuelirem.studentassistant.repository;


import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>, Repository<Course, Long> {

    List<Course> findCoursesByProgramsContains(Program program);

    List<Course> findCoursesByLecturersContains(Lecturer lecturer);

    Course findCourseByLecturersContains(Lecturer lecturer);
}
