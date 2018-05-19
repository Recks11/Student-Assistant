package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.RegexService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/register/")
public class RegisterController {

    private final RegexService regexService;
    private final StudentService studentService;
    private final LecturerService lecturerService;

    public RegisterController(StudentService studentService,
                              RegexService regexService, LecturerService lecturerService) {
        this.studentService = studentService;
        this.regexService = regexService;
        this.lecturerService = lecturerService;
    }

    @PostMapping("/student")
    public Mono<Student> registerStudent(@Valid @ModelAttribute Student student){
        return studentService.save(student);
    }

    @PostMapping("/lecturer")
    public Mono<Lecturer> registerLecturer(@Valid @ModelAttribute Student student){
        return lecturerService.fromStudent(student).flatMap(lecturerService::save);
    }
}
