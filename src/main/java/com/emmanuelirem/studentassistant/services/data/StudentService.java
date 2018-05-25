package com.emmanuelirem.studentassistant.services.data;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentService {

    Mono<Student> getLoggedInStudentFromRequest(WebRequest request);
    Mono<Student> findById(String id);
    Mono<Student> findByRegistrationNumber(String registrationNumber);
    Flux<Course> findUnregisteredCoursesForStudent(String id, String programId);
    Flux<Student> findStudentsOfferingCourse(Course course);
    Mono<Student> save(Student student);
    Mono<Student> update(Student student);
    Flux<Student> saveAll(List<Student> studentList);
    Mono<Student> registerCourse(Course course, Student student);
    Flux<Course> registerCourses(List<Course> courses, Student student);
    Mono<Student> removeCourse(Student student, Course course);
    Flux<Course> removeCourse(String studentId, String courseId);
}
