package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student/course")
public class StudentCourseController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentCourseController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/view/{id}")
    public String viewCourse(@PathVariable("id") long id, HttpServletRequest request, Model model) {
        Student student = studentService.getLoggedInStudentFromRequest(request);

        List<Course> courses = student.getCourses();
        Course course = courseService.findCourseById(id);
        if (courses.contains(course)){
            model.addAttribute("course", course);
        }

        return "studentViewCourse";
    }

    @ModelAttribute("loggedInStudent")
    public Student addStudentToModel(Principal principal){
        return studentService.findByRegistrationNumber(principal.getName());
    }
}
