package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.CourseRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveOrUpdate(Course course){
        courseRepository.save(course);
    }

    @Override
    public List<Course> findCoursesWithLecturer(Lecturer lecturer) {
        return courseRepository.findCoursesByLecturersContains(lecturer);
    }

    @Override
    public Course findCourseById(long id) {
        if(courseRepository.findById(id).isPresent())
            return courseRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public Course findCourseContainingLecturer(Lecturer lecturer) {
        return courseRepository.findCourseByLecturersContains(lecturer);
    }

    @Override
    public List<Course> findCoursesContainingProgram(Program program) {
        return courseRepository.findCoursesByProgramsContains(program);
    }

    @Override
    public List<Course> findCoursesByIds(List<Long> idList) {

        List<Course> courses = new ArrayList<>();
        idList.forEach(id -> {
            courses.add(this.findCourseById(id));
        });
        return courses;
    }
}
