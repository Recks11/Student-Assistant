package com.emmanuelirem.studentassistant.controllers.Api.V1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApiDummy {

    @GetMapping(path = {"index.html", "login", "","register"})
    public String home() {
        return "index";
    }
}
