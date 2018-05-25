package com.emmanuelirem.studentassistant.services.data;

import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.v2Mapper.CollegeDTO;
import reactor.core.publisher.Flux;

public interface CollegeService {

    Flux<College> findAll();

    Flux<CollegeDTO> findAllColleges();
}
