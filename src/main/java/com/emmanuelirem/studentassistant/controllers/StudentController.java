package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.CollegeRepository;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.repository.ProgramRepository;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProgramRepository programRepository;


    @Autowired
    public StudentController(StudentRepository studentRepository, CollegeRepository collegeRepository, DepartmentRepository departmentRepository, ProgramRepository programRepository) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
        this.programRepository = programRepository;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal().getName());
        return "studentHome";
    }

    @GetMapping("/courses")
    public String getCourses(Model model, HttpServletRequest request, @RequestParam(name = "add-courses", required = true,defaultValue = "false") boolean addCourses) {

        Student student = studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());
        if(student.getProgram()==null) {

            model.addAttribute("programs", programRepository.findProgramsByDepartment_Name(DepartmentsEnum.Computer_and_Information_Sciences.name().replace('_',' ')));
        }
        if (addCourses) {
            model.addAttribute("course", new Course());
        }

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
