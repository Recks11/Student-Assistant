package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {


    private LecturerService lecturerService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public LecturerController(LecturerService lecturerService, StudentService studentService, CourseService courseService) {
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/account")
    public Mono<Lecturer> viewAccount(WebRequest request){
        return lecturerService.getLecturerFromRequest(request);
    }

    @PostMapping("/account")
    public Mono<Lecturer> updateAccount(WebRequest request, @RequestBody Lecturer lecturer){
        return lecturerService.getLecturerFromRequest(request).flatMap(
                lecturer1 -> {
                    lecturer1 = lecturer;
                    return lecturerService.update(lecturer1);
                }
        );

    }

    @GetMapping("/status/inOffice")
    public Mono<Lecturer> setInOffice(WebRequest request){
        return lecturerService.getLecturerFromRequest(request).flatMap(
                lecturer -> {
                    lecturer.setInOffice(true);
                    return lecturerService.update(lecturer);
                }
        );
    }
    @GetMapping("/status/notAvailable")
    public Mono<Lecturer> setNotAvailable(WebRequest request){
        return lecturerService.getLecturerFromRequest(request).flatMap(
                lecturer -> {
                    lecturer.setInOffice(false);
                    return lecturerService.update(lecturer);
                }
        );
    }
    @PostMapping("/department")
    public Mono<Lecturer> addDepartment(@RequestBody Department department, WebRequest request){

        return lecturerService.getLecturerFromRequest(request)
                .flatMap(lecturer -> {
                    lecturer.addDepartment(department);
                    return lecturerService.update(lecturer);
                });
    }
}
