package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.enums.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ProgramService programService;

    @Autowired
    public CourseController(StudentService studentService, CourseService courseService, ProgramService programService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.programService = programService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> getCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Course> viewCourses(@PathVariable String id){
        return courseService.findCourseById(id);
    }

    @GetMapping("/{id}/students")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Student> studentsOfferingLecturerCourse(@PathVariable String id) {
        return courseService.findCourseById(id)
                .flatMapMany(studentService::findStudentsOfferingCourse);
    }
}
