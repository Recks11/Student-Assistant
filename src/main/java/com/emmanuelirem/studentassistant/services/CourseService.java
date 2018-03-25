package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.university.Program;

import java.util.List;

public interface CourseService {

    List<Course> findCoursesContainingProgram(Program program);

    Course findCourseById(long id);
}
