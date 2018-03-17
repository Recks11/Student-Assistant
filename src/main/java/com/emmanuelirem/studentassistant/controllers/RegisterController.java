package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.Models.Security.Roles;
import com.emmanuelirem.studentassistant.Models.Security.Users;
import com.emmanuelirem.studentassistant.Models.Student;
import com.emmanuelirem.studentassistant.Repository.RolesService;
import com.emmanuelirem.studentassistant.Repository.StudentRepository;
import com.emmanuelirem.studentassistant.Repository.UsersService;
import com.emmanuelirem.studentassistant.Services.EncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final EncoderService encoderService;

    private StudentRepository studentRepository;

    private final UsersService usersService;
    private final RolesService rolesService;
    @Autowired
    public RegisterController(StudentRepository studentRepository, EncoderService encoderService,
                              UsersService usersService, RolesService rolesService){
        this.studentRepository = studentRepository;
        this.encoderService = encoderService;
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){

        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student){

        try{
            Users newUser = new Users();
            Roles userRole = new Roles();
            newUser.setRegistrationNumber(student.getRegistrationNumber());
            newUser.setPassword(encoderService.passwordEncoder().encode(student.getPassword()));
            newUser.setEnabled(true);

            userRole.setRegistrationNumber(student.getRegistrationNumber());
            userRole.setRole("ROLE_USER");

            usersService.save(newUser);
            rolesService.save(userRole);

            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/";
    }
}
