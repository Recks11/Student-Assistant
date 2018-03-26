package com.emmanuelirem.studentassistant.controllers;

import com.emmanuelirem.studentassistant.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {


    private LecturerService lecturerService;


}
