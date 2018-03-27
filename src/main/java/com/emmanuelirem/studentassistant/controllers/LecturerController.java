package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.services.LecturerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {


    private LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/")
    public String openLecturerHome(){

        return "lecturerHome";
    }
}
