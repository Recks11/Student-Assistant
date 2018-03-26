package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LecturerService {

    void saveOrUpdateLecturer(Lecturer lecturer);

    void addCourseToLecturer(Lecturer lecturer, Course course);

    void removeCourseFromLecturer(Lecturer lecturer, Course course);

    List<Course> getCoursesWithLecturer(Lecturer lecturer);

    Lecturer getLecturerByIdentifier(String name);

    Lecturer getLecturerFromRequest(HttpServletRequest request);

    void setOrUpdateCoursePassword(Course course, String password);
}
