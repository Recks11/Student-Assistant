package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.repository.LecturerRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class LecturerServiceImpl implements LecturerService{

    private LecturerRepository lecturerRepository;
    private CourseService courseService;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseService courseService) {
        this.lecturerRepository = lecturerRepository;
        this.courseService = courseService;
    }

    @Override
    public void saveOrUpdateLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    @Override// TODO remove debug
    public void addCourseToLecturer(Lecturer lecturer, Course course) {
        if(!lecturer.getCourses().contains(course)){
            lecturer.addCourse(course);
            this.saveOrUpdateLecturer(lecturer);
        } else {
            System.out.println("Lecturer doesn't lecture course");
        }
    }

    @Override
    public void removeCourseFromLecturer(Lecturer lecturer, Course course) {
        if(lecturer.getCourses().contains(course)){
            lecturer.removeCourse(course);
            this.saveOrUpdateLecturer(lecturer);
        }
    }

    @Override
    public List<Course> getCoursesWithLecturer(Lecturer lecturer) {
        return courseService.findCoursesWithLecturer(lecturer);
    }

    @Override
    public Lecturer getLecturerByIdentifier(String name) {
        return lecturerRepository.findLecturerByUsername(name);
    }

    @Override
    public Lecturer getLecturerFromRequest(HttpServletRequest request) {
        return lecturerRepository.findLecturerByUsername(request.getUserPrincipal().getName());
    }

    @Override
    public void setOrUpdateCoursePassword(Course course, String password) {
        course.setPassword(password);
        courseService.saveOrUpdate(course);
    }
}
