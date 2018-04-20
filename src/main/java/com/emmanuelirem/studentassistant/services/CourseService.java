package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Program;

import java.util.List;

public interface CourseService {

    void saveOrUpdate(Course course);

    Course findCourseById(long id);

    Course findCourseContainingLecturer(Lecturer lecturer);

    List<Course> findCoursesContainingProgram(Program program);

    List<Course> findCoursesWithLecturer(Lecturer lecturer);

    List<Course> findCoursesByIds(List<Long> idList);
}
