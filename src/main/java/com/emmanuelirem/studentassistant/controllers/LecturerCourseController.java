package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Controller
@RequestMapping("/api/v1/lecturer/courses")
public class LecturerCourseController {

    private final CourseService courseService;
    private final LecturerService lecturerService;
    private final DepartmentRepository departmentRepository;
    private final ProgramService programService;
    private final StudentService studentService;

    @Autowired
    public LecturerCourseController(CourseService courseService, LecturerService lecturerService, DepartmentRepository departmentRepository, ProgramService programService, StudentService studentService) {
        this.courseService = courseService;
        this.lecturerService = lecturerService;
        this.departmentRepository = departmentRepository;
        this.programService = programService;
        this.studentService = studentService;
    }


    @GetMapping
    public Flux<Course> getCourses(Principal principal) {
        return lecturerService.getLecturerByIdentifier(principal.getName()).flatMapIterable(Lecturer::getCourses);
    }

    @GetMapping("/{id}/students")
    public Flux<Student> studentsOfferingLecturerCourse(@PathVariable String id) {
        return courseService.findCourseById(id).flatMapMany(studentService::findStudentsOfferingCourse);
    }

    @GetMapping("/{id}")
    public Mono<Course> viewCourse(@PathVariable String id) {
        return courseService.findCourseById(id);
    }

    @GetMapping("/add/{id}")
    public Mono<Lecturer> addCourse(@PathVariable("id") String id, WebRequest request) {
        return courseService.findCourseById(id).flatMap(
                course -> lecturerService.getLecturerFromRequest(request).map(
                        lecturer -> {
                            if(!lecturer.getCourses().contains(course))
                                lecturer.addCourse(course);
                            return lecturer;
                        }
                )
        );
    }

    @GetMapping("/remove/{id}")
    public Mono<Lecturer> removeCourse(@PathVariable("id") String id, WebRequest request) {

        return courseService.findCourseById(id).flatMap(
                course -> lecturerService.getLecturerFromRequest(request).map(
                        lecturer -> {
                            if (lecturer.getCourses().contains(course))
                                lecturer.removeCourse(course);
                            return lecturer;
                        }
                )
        );
    }
}
