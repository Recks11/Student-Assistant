package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.SortService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ProgramService programService;
    private final SortService sortService;

    @Autowired
    public StudentController(StudentService studentService, ProgramService programService, SortService sortService) {
        this.studentService = studentService;
        this.programService = programService;
        this.sortService = sortService;
    }

    @GetMapping
    public Mono<Student> addStudentToModel(@AuthenticationPrincipal Mono<Users> principal){
        return principal.flatMap(p -> studentService.findByRegistrationNumber(p.getUsername()));
    }

    @GetMapping("/{id}/courses")
    public Flux<Course> getRegisteredCourses(@PathVariable String id) {
        return studentService.findById(id).flatMapIterable(Student::getCourses);
    }

    @PostMapping("/{id}/courses")
    public Flux<Course> addCourses(@PathVariable String id, @RequestBody ListHelper<Course> courseList) {
        return studentService.findById(id).flatMapIterable(
                student -> {
                    courseList.getItem().forEach(student::addCourse);
                    studentService.save(student);
                    return student.getCourses();
                });
    }

    @GetMapping("/{id}/program")
    public Mono<Program> getProgram (@PathVariable String id){
        return studentService.findById(id).flatMap(
                student -> Mono.just(student.getProgram())
        );
    }

    @PostMapping("/{id}/program")
    public Mono<Student> addDepartment(@PathVariable String id, @RequestBody Program program) {

        return studentService.findById(id).flatMap(
                student -> {
                    student.setProgram(program);
                    return studentService.update(student);
                }
        );
    }


}