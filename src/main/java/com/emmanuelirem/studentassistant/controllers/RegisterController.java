package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.repository.RolesService;
import com.emmanuelirem.studentassistant.repository.UsersService;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.RegexService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final RegexService regexService;
    private final StudentService studentService;
    private final LecturerService lecturerService;

    public RegisterController(StudentService studentService,
                              RegexService regexService, LecturerService lecturerService) {
        this.studentService = studentService;
        this.regexService = regexService;
        this.lecturerService = lecturerService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){

        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute Student student, BindingResult result){

        if (regexService.matchesStudentRegNumber(student.getRegistrationNumber())){
            try{
                studentService.save(student);
                return "redirect:/login";
            } catch (Exception e) {
                e.getMessage();
                return "register";
            }
        }

        if (regexService.matchesLecturerId(student.getRegistrationNumber())){
            try{
                Lecturer lecturer = lecturerService.fromStudent(student);
                lecturerService.save(lecturer);
                return "redirect:/login";
            } catch (Exception e) {
                e.getMessage();
                return "register";
            }
        }



        return "register";
    }
}
