package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lecturer/courses")
public class LecturerCourseController {

    private final CourseService courseService;
    private final LecturerService lecturerService;
    private final DepartmentRepository departmentRepository;
    private final ProgramService programService;

    @Autowired
    public LecturerCourseController(CourseService courseService, LecturerService lecturerService, DepartmentRepository departmentRepository, ProgramService programService) {
        this.courseService = courseService;
        this.lecturerService = lecturerService;
        this.departmentRepository = departmentRepository;
        this.programService = programService;
    }


    @GetMapping("/")
    public String viewCourses(Model model, HttpServletRequest request) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", new Department());
        Lecturer lecturer = lecturerService.getLecturerFromRequest(request);

        if (!lecturer.getDepartments().isEmpty()){
            List<Program> programList = new ArrayList<>();
            lecturer.getDepartments().forEach(department ->
                    programList.addAll(programService.findProgramsByDepartment_Name(department.getName()))
            );
            model.addAttribute("programs", programList);
        }

        return "lecturerCourses";
    }
    @GetMapping("/view/{id}")
    public String viewCourse(@PathVariable long id, HttpServletRequest request, Model model){

        Course course = courseService.findCourseById(id);
        Lecturer lecturer = lecturerService.getLecturerFromRequest(request);

        if(lecturer.getCourses().contains(course)){
            model.addAttribute("course", course);
            return "LecturerViewCourse";
        }
        return "redirect:/lecturer/";
    }

    @GetMapping("/add/{id}")
    public String addCourse(@PathVariable("id") long id, HttpServletRequest request){

        Course course = courseService.findCourseById(id);
        if(course!=null){
            Lecturer lecturer = lecturerService.getLecturerFromRequest(request);
            lecturer.addCourse(course);
            lecturerService.update(lecturer);
        }
        return "redirect:/lecturer/courses/";
    }
    @GetMapping("/remove/{id}")
    public String removeCourse(@PathVariable("id") long id, HttpServletRequest request){

        Course course = courseService.findCourseById(id);
        if(course!=null){
            Lecturer lecturer = lecturerService.getLecturerFromRequest(request);
            lecturer.removeCourse(course);
            lecturerService.update(lecturer);
        }
        return "redirect:/lecturer/courses/";
    }

    @ModelAttribute("lecturer")
    public Lecturer getCurrentLecturer(Principal principal){
        return lecturerService.getLecturerByIdentifier(principal.getName());
    }
}
