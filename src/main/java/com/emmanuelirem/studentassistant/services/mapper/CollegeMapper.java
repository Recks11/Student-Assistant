package com.emmanuelirem.studentassistant.services.mapper;

import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.v2Mapper.CollegeDTO;

public interface CollegeMapper {

    public CollegeDTO collegeToCollegeDTO(College college);

    public College collegeDtoToCollege(CollegeDTO collegeDTO);
}
