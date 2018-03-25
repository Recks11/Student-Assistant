package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.CourseRepository;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{


    private final CourseService courseService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Student getLoggedInStudentFromRequest(HttpServletRequest request) {
        return studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Course> findUnregisteredCoursesForStudent(Student student) {
        Program program = student.getProgram();
        List<Course> listOfProgramCourses = courseService.findCoursesContainingProgram(program);
        List<Course> listOfRegisteredCourses = student.getCourses();
        List<Course> listOfCoursesToBeRegistered = new ArrayList<>();

        listOfProgramCourses.forEach(course -> {
            if (!listOfRegisteredCourses.contains(course)) {
                listOfCoursesToBeRegistered.add(course);
            }
        });

        return listOfCoursesToBeRegistered;
    }
}
