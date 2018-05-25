package com.emmanuelirem.studentassistant.controllers.Api.V1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApiDummy {

    @GetMapping()
    public String api() {
        return "index";
    }
}
