package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.repository.CourseRepository;
import com.emmanuelirem.studentassistant.services.data.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Mono<Course> saveOrUpdate(Course course){
        return courseRepository.save(course);
    }


    @Override
    public Flux<Course> saveAll(Flux<Course> courseFlux) {
        return courseRepository.saveAll(courseFlux);
    }

    @Override
    public Flux<Course> findCoursesWithLecturer(Lecturer lecturer) {
        return courseRepository.findCoursesByLecturersContains(lecturer);
    }

    @Override
    public Mono<Course> findCourseById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public Flux<Course> findCoursesByIds(List<String> idList) {
        return courseRepository.findAllById(idList);
    }

    @Override
    public Mono<Course> setOrUpdateCoursePassword(Course course, String password) {
        course.setPassword(password);
        return this.saveOrUpdate(course);
    }

    @Override
    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }
}
