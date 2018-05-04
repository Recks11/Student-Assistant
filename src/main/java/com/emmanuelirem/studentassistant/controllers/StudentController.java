package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.Program;

import java.lang.String;
import java.security.Principal;
import java.util.List;

import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.SortService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ProgramService programService;
    private final SortService sortService;

    @Autowired
    public StudentController(StudentService studentService, ProgramService programService, SortService sortService) {
        this.studentService = studentService;
        this.programService = programService;
        this.sortService = sortService;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        List<Course> registeredCourses =
                studentService.getLoggedInStudentFromRequest(request).getCourses();

        registeredCourses.sort(sortService);

        model.addAttribute("registeredCourses", registeredCourses);
        return "studentHome";
    }


    @PostMapping("/program")
    public String addDepartment(HttpServletRequest request, @ModelAttribute("program") Program program) {

        Student student = studentService.getLoggedInStudentFromRequest(request);
        Program studentProgram = programService.findProgramByName(program.getName());
        student.setProgram(studentProgram);
        studentService.save(student);

        return "redirect:/student/course/register";
    }


    @ModelAttribute("loggedInStudent")
    public Student addStudentToModel(Principal principal){
        return studentService.findByRegistrationNumber(principal.getName());
    }

}