package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.enums.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.helper.ListHelper;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.ProgramService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/student/course")
public class StudentCourseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ProgramService programService;

    @Autowired
    public StudentCourseController(StudentService studentService, CourseService courseService, ProgramService programService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.programService = programService;
    }

//    @GetMapping("/register")
//    public String getCourses(Model model,
//                             HttpServletRequest request,
//                             @RequestParam(name = "add-courses", required = false, defaultValue = "false") boolean addCourses) {
//        //student class used to check student status for form
//        Student student = studentService.getLoggedInStudentFromRequest(request);
//
//        //if student doesn't have a program
//        if(student.getProgram()==null) {
//
//            String studentProgram = DepartmentsEnum.Computer_and_Information_Sciences.name().replace('_',' ');
//            model.addAttribute("programs", programService.findProgramsByDepartment_Name(studentProgram));
//        }
//
//
//        //if student has a program add course list and list of students courses to model
//        if (student.getProgram() != null) {
//            List<Course> listOfCoursesToBeRegistered = studentService.findUnregisteredCoursesForStudent(student);
//            List<Course> listOfRegisteredCourses = student.getCourses();
//
//            model.addAttribute("listOfCoursesToBeRegistered", listOfCoursesToBeRegistered);
//            model.addAttribute("listOfRegisteredCourses", listOfRegisteredCourses);
//        }
//
//
//
//        model.addAttribute("addCourse", addCourses);//add-course variable = true
//        model.addAttribute("student", student);
//        model.addAttribute("program", new Program());
//        model.addAttribute("listOfSelectedCourses", new ListHelper<Integer>());
//
//        return "courses";
//    }
//
//    @GetMapping("/view")
//    public String viewCourses(Model model, HttpServletRequest request){
//        Student student = studentService.getLoggedInStudentFromRequest(request);
//
//        model.addAttribute("registeredCourses", student.getCourses());
//        return "studentViewAllCourses";
//    }
//
//    @GetMapping("/view/{id}")
//    public String viewCourse(@PathVariable("id") String id, HttpServletRequest request, Model model) {
//        Student student = studentService.getLoggedInStudentFromRequest(request);
//
//        List<Course> courses = student.getCourses();
//        Course course = courseService.findCourseById(id);
//        if (courses.contains(course)){
//            model.addAttribute("course", course);
//        }
//
//        return "studentViewCourse";
//    }
//
//    @PostMapping("/addSelected")
//    public String registerCourses(HttpServletRequest request,
//                                  @ModelAttribute("listOfSelectedCourses") ListHelper<Long> courseIdList) {
//
//        Student student = studentService.getLoggedInStudentFromRequest(request);
//        List<Course> courses = courseService.findCoursesByIds(courseIdList.getLongValue());
//        studentService.registerCourses(courses, student);
//
//        return "redirect:/student/course/register";
//    }
//
//    @GetMapping("/remove/{id}")
//    public String removeCourse(HttpServletRequest request,@PathVariable("id") String id) {
//
//        Student student = studentService.getLoggedInStudentFromRequest(request);
//        Course course = courseService.findCourseById(id);
//        studentService.removeCourse(student, course);
//        return "redirect:/student/course/register";
//    }
//
//    @ModelAttribute("loggedInStudent")
//    public Student addStudentToModel(Principal principal){
//        return studentService.findByRegistrationNumber(principal.getName());
//    }
}
