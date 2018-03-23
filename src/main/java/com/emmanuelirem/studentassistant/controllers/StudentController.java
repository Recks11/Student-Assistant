package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.*;
import java.lang.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final ProgramRepository programRepository;


    @Autowired
    public StudentController(StudentRepository studentRepository, CollegeRepository collegeRepository, DepartmentRepository departmentRepository, ProgramRepository programRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal().getName());
        return "studentHome";
    }

    @GetMapping("/courses")
    public String getCourses(Model model, HttpServletRequest request, @RequestParam(name = "add-courses", required = true,defaultValue = "false") boolean addCourses) {

        Student student = studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());

        if (student.getProgram() != null) {
            Program program = programRepository.findProgramByName(student.getProgram().getName());
            model.addAttribute("courseList", courseRepository.findCoursesByProgramsContains(program));
            model.addAttribute("stringArray", new ArrayList<Long>());
        }

        if(student.getProgram()==null) {

            model.addAttribute("programs", programRepository.findProgramsByDepartment_Name(DepartmentsEnum.Computer_and_Information_Sciences.name().replace('_',' ')));
        }

        model.addAttribute("course", new Course());



        model.addAttribute("addCourse", addCourses);
        model.addAttribute("student", student);
        model.addAttribute("program", new Program());

        return "courses";
    }

    @PostMapping("/program")
    public String addDepartment(HttpServletRequest request, @ModelAttribute("program") Program program) {

        Student student = studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());
        Program studentProgram = programRepository.findProgramByName(program.getName());

        student.setProgram(studentProgram);
        studentRepository.save(student);

        return "redirect:/student/courses";
    }

    @PostMapping("/courses")
    public String registerCourses() {


        return "courses";
    }
}