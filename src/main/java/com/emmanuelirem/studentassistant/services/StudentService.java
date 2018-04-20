package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {

    Student getLoggedInStudentFromRequest(HttpServletRequest request);
    Student findByRegistrationNumber(String registrationNumber);
    List<Course> findUnregisteredCoursesForStudent(Student Student);
    List<Student> findStudentsOfferingCourse(Course course);
    void save(Student student);
    void update(Student student);
    void saveAll(List<Student> studentList);
    void registerCourse(Course course, Student student);
    void registerCourses(List<Course> courses, Student student);
    void removeCourse(Student student, Course course);
}
