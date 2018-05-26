package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.data.CourseService;
import com.emmanuelirem.studentassistant.services.data.ProgramService;
import com.emmanuelirem.studentassistant.services.data.SortService;
import com.emmanuelirem.studentassistant.services.data.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> addStudentToModel(@AuthenticationPrincipal Mono<Users> principal) {
        if (principal == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please Log In"));
        }

        return principal.flatMap(p -> studentService.findByRegistrationNumber(p.getUsername()))
                .doOnError(throwable -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please Log In");
                });
    }

    @GetMapping("/{id}/courses")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> getRegisteredCourses(@PathVariable String id) {
        return studentService.findById(id).flatMapIterable(Student::getCourses);
    }

    @GetMapping("/{id}/courses/unregistered")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> getUnRegisteredCourses(@PathVariable String id) {
        return studentService.findById(id)
                .flatMapMany(
                        student -> studentService.findUnregisteredCoursesForStudent(student.getId(), student.getProgram().getId()));
    }

    @PostMapping("/{id}/courses")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> addCourses(@PathVariable String id, @RequestBody ArrayList<Course> courseList) {

        return studentService.findById(id)
                .flatMapMany(
                        student -> studentService.registerCourses(courseList, student));
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> deleteCourses(@PathVariable String id, @PathVariable("courseId") String courseId) {
        return studentService.removeCourse(id, courseId);
    }

    @GetMapping("/{id}/program")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Program> getProgram(@PathVariable String id) {
        return studentService.findById(id).flatMap(
                student -> Mono.just(student.getProgram())
        );
    }

    @PostMapping("/{id}/program")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> addDepartment(@PathVariable String id, @RequestBody Program program) {

        return studentService.findById(id).flatMap(
                student -> {
                    student.setProgram(program);
                    return studentService.update(student);
                }
        );
    }


}