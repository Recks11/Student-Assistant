package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.services.data.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {

    private LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> getCurrentLecturer(@AuthenticationPrincipal Mono<Principal> principal) {
        if(principal == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please Log In"));
        }

        return principal.flatMap(
                userPrincipal -> lecturerService.getLecturerByIdentifier(userPrincipal.getName())
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> viewAccount(@PathVariable String id) {
        return lecturerService.getLecturerById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> updateAccount(@PathVariable String id, @RequestBody Lecturer lecturer) {
        return lecturerService.getLecturerById(id)
            .flatMap(
                returnedLecturer -> {
                    returnedLecturer = lecturer; //This is bad practice DO NOT DO THIS IN PRODUCTION HOE
                    return lecturerService.update(returnedLecturer);
                }
            );
    }

    @GetMapping("/{id}/status/toggleStatus")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> setInOffice(@PathVariable String id) {
        return lecturerService.getLecturerById(id)
            .flatMap(
                lecturer -> {
                    lecturer.setInOffice(!lecturer.isInOffice());
                    return lecturerService.update(lecturer);
                }).then();
    }

    @PostMapping("/{id}/department")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> addDepartment(@RequestBody Department department, @PathVariable String id) {
        return lecturerService.getLecturerById(id)
            .flatMap(lecturer -> {
                lecturer.addDepartment(department);
                return lecturerService.update(lecturer);
            });
    }

    @GetMapping("/{id}/course")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> getCourses(@PathVariable String id) {
        return lecturerService.getLecturerById(id)
                .flatMapIterable(Lecturer::getCourses);
    }

    @PostMapping("/{id}/course")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Lecturer> addCourses(@PathVariable String id,
                                     @RequestBody Course course) {

        return lecturerService.addCourseToLecturer(id, course);
    }

    @DeleteMapping("/{id}/course/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> removeCourse(@PathVariable("id") String id, @PathVariable("courseId") String courseId) {

        return lecturerService.removeCourseFromLecturer(id, courseId);
    }
}
