package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.Models.Student;
import com.emmanuelirem.studentassistant.Repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private StudentRepository studentRepository;

    public RegisterController (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){

        model.addAttribute("student", new Student());
        return "register";
    }
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student){

        try{
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/";
    }
}
