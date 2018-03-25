package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {

    Student findByRegistrationNumber(String registrationNumber);
    List<Course> findUnregisteredCoursesForStudent(Student Student);
    void save(Student student);
    Student getLoggedInStudentFromRequest(HttpServletRequest request);
}
