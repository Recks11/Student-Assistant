package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.SortService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final ProgramService programService;
    private final SortService sortService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, ProgramService programService, SortService sortService, CourseService courseService) {
        this.studentService = studentService;
        this.programService = programService;
        this.sortService = sortService;
        this.courseService = courseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> addStudentToModel(@AuthenticationPrincipal Mono<Users> principal) {
        return principal.flatMap(p -> studentService.findByRegistrationNumber(p.getUsername()));
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
                .flatMapMany(student -> studentService.findUnregisteredCoursesForStudent(student.getId(), student.getProgram().getId()));
    }

    @PostMapping("/{id}/courses")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> addCourses(@PathVariable String id, @RequestBody ListHelper<Course> courseList) {
        return studentService.findById(id).flatMapIterable(
                student -> {
                    courseList.getItem().forEach(student::addCourse);
                    studentService.save(student);
                    return student.getCourses();
                });
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> deleteCourses(@PathVariable String id, @PathVariable("courseId") String courseId) {
        return courseService.findCourseById(courseId)
                .flatMap(course -> studentService.findById(id).flatMap(
                        student -> {
                            student.removeCourse(course);
                            return studentService.update(student);
                        }));
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