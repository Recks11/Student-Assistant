package com.emmanuelirem.studentassistant.controllers.Api.V2;


import com.emmanuelirem.studentassistant.models.v2Mapper.CollegeDTO;
import com.emmanuelirem.studentassistant.services.data.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v2/colleges")
public class V2CollegeController {

    private final CollegeService collegeService;

    @Autowired
    public V2CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<CollegeDTO> getAllColleges() {
        return collegeService.findAllColleges();
    }
}
