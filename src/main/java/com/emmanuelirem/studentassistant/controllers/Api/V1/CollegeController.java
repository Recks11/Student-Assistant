package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.services.data.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/colleges")
public class CollegeController {

    private final CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<College> getAllColleges() {
        return collegeService.findAll();
    }
}
