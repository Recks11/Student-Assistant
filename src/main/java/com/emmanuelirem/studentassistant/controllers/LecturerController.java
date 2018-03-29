package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {


    private LecturerService lecturerService;
    private final StudentService studentService;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public LecturerController(LecturerService lecturerService, StudentService studentService, DepartmentRepository departmentRepository) {
        this.lecturerService = lecturerService;
        this.studentService = studentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public String openLecturerHome(Model model, @ModelAttribute("lecturer") Lecturer lecturer){
        if(!lecturer.getCourses().isEmpty()){
            List<Student> studentList = new ArrayList<>();
            lecturer.getCourses().forEach(course -> studentList.addAll(studentService.findStudentsOfferingCourse(course)));
                if(!studentList.isEmpty()){
                    model.addAttribute("students", studentList);
                }
        }

        return "lecturerHome";
    }

    @GetMapping("/account")
    public String viewAccount(){


        return "lecturerAccountPage";
    }

    @PostMapping("/department")
    public String addDepartment(@ModelAttribute("department") Department department, HttpServletRequest request){

        Lecturer lecturer = this.lecturerService.getLecturerFromRequest(request);
        Department selectedDepartment = departmentRepository.findDepartmentByName(department.getName());
        lecturer.addDepartment(selectedDepartment);
        lecturerService.update(lecturer);
        return "redirect:/lecturer/courses/";
    }

    @ModelAttribute("listOfCoursesByLecturer")
    public List<Course> addCoursesListToModel(Principal principal){
        Lecturer lecturer = lecturerService.getLecturerByIdentifier(principal.getName());
        List<Course> listOfCoursesByLecturer = lecturerService.getCoursesWithLecturer(lecturer);
        if(listOfCoursesByLecturer != null)
            return listOfCoursesByLecturer;
        else
            return new ArrayList<>();
    }

    @ModelAttribute("lecturer")
    public Lecturer getCurrentLecturer(Principal principal){
        return lecturerService.getLecturerByIdentifier(principal.getName());
    }
}
