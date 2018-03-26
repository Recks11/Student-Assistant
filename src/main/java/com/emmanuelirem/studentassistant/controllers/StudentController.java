package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;
import com.emmanuelirem.studentassistant.models.enums.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.university.Program;

import java.lang.String;
import java.security.Principal;
import java.util.List;

import com.emmanuelirem.studentassistant.services.CourseService;
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
    private final CourseService courseService;
    private final SortService sortService;

    @Autowired
    public StudentController(StudentService studentService, ProgramService programService, CourseService courseService, SortService sortService) {
        this.studentService = studentService;
        this.programService = programService;
        this.courseService = courseService;
        this.sortService = sortService;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        List<Course> registeredCourses = studentService.getLoggedInStudentFromRequest(request)
                .getCourses();

        registeredCourses.sort(sortService);

        model.addAttribute("registeredCourses", registeredCourses);
        return "studentHome";
    }

    @GetMapping("/courses")
    public String getCourses(Model model,
                             HttpServletRequest request,
                             @RequestParam(name = "add-courses", required = false, defaultValue = "false") boolean addCourses) {



        //student class used to check student status for form
        Student student = studentService.getLoggedInStudentFromRequest(request);

        //if student doesn't have a program
        if(student.getProgram()==null) {

            String studentProgram = DepartmentsEnum.Computer_and_Information_Sciences.name().replace('_',' ');
            model.addAttribute("programs", programService.findProgramsByDepartment_Name(studentProgram));
        }


        //if student has a program add course list and list of students courses to model
        if (student.getProgram() != null) {
            List<Course> listOfCoursesToBeRegistered = studentService.findUnregisteredCoursesForStudent(student);
            List<Course> listOfRegisteredCourses = student.getCourses();

            model.addAttribute("listOfCoursesToBeRegistered", listOfCoursesToBeRegistered);
            model.addAttribute("listOfRegisteredCourses", listOfRegisteredCourses);
        }



        model.addAttribute("addCourse", addCourses);//add-course variable = true
        model.addAttribute("student", student);
        model.addAttribute("program", new Program());
        model.addAttribute("listOfSelectedCourses", new ListHelper());

        return "courses";
    }

    @PostMapping("/program")
    public String addDepartment(HttpServletRequest request, @ModelAttribute("program") Program program) {

        Student student = studentService.getLoggedInStudentFromRequest(request);
        Program studentProgram = programService.findProgramByName(program.getName());
        student.setProgram(studentProgram);
        studentService.save(student);

        return "redirect:/student/courses";
    }

    @PostMapping("/courses")
    public String registerCourses(HttpServletRequest request,
                                  @ModelAttribute("listOfSelectedCourses") ListHelper courseList) {

        Student student = studentService.findByRegistrationNumber(request.getUserPrincipal().getName());

        courseList.getCoursesList().forEach(student::addCourse);
        studentService.save(student);

        return "redirect:/student/courses";
    }

    @GetMapping("/courses/remove/{id}")
    public String removeCourse(HttpServletRequest request,@PathVariable("id") Long id) {

        Student student = studentService.getLoggedInStudentFromRequest(request);
        Course course = courseService.findCourseById(id);
        if (course !=  null)
            student.removeCourse(course);
        studentService.save(student);
        return "redirect:/student/courses";
    }

    @ModelAttribute
    public void addStudent(Model model, Principal principal){
        model.addAttribute("loggedInStudent",studentService.findByRegistrationNumber(principal.getName()));
    }

}