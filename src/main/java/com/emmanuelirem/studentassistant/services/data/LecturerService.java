package com.emmanuelirem.studentassistant.services.data;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LecturerService {

    Mono<Lecturer> fromStudent(Student student);

    Mono<Lecturer> save(Lecturer lecturer);

    Mono<Lecturer> update(Lecturer lecturer);

    Flux<Lecturer> getAll();

    Mono<Lecturer> getLecturerById(String id);

    Mono<Lecturer> addCourseToLecturer(String lecturerId, Course course);

    Mono<Lecturer> removeCourseFromLecturer(String lecturerId, String courseId);

    Flux<Lecturer> getLecturersForCourses(Course course);

    Mono<Lecturer> getLecturerByIdentifier(String name);

    Mono<Lecturer> getLecturerFromRequest(WebRequest request);
}
