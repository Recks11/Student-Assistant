package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
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
                lecturer1 -> {
                    lecturer1 = lecturer;
                    return lecturerService.update(lecturer1);
                }
            );
    }

    @GetMapping("/{id}/status/inOffice")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> setInOffice(@PathVariable String id) {
        return lecturerService.getLecturerById(id)
            .flatMap(
                lecturer -> {
                    lecturer.setInOffice(true);
                    return lecturerService.update(lecturer);
                }
            );
    }

    @GetMapping("/{id}/status/notAvailable")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> setNotAvailable(WebRequest request) {
        return lecturerService.getLecturerFromRequest(request).flatMap(
            lecturer -> {
                lecturer.setInOffice(false);
                return lecturerService.update(lecturer);
            }
        );
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

        return lecturerService.getLecturerById(id)
                .flatMap(lecturer -> {
                    lecturer.addCourse(course);
                    return lecturerService.update(lecturer);
                });
    }

    @DeleteMapping("/{id}/course")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Lecturer> removeCourse(@PathVariable("id") String id, @RequestBody Course course) {

        return lecturerService.getLecturerById(id)
                .flatMap(lecturer -> {
                    lecturer.removeCourse(course);
                    return lecturerService.update(lecturer);
                });
    }
}
