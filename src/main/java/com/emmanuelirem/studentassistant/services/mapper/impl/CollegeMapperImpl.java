package com.emmanuelirem.studentassistant.services.mapper.impl;

import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.v2Mapper.CollegeDTO;
import com.emmanuelirem.studentassistant.services.mapper.CollegeMapper;
import org.springframework.stereotype.Component;

@Component
public class CollegeMapperImpl implements CollegeMapper {

    @Override
    public CollegeDTO collegeToCollegeDTO(College college) {
        CollegeDTO collegeDTO = new CollegeDTO();
        collegeDTO.setName(college.getName());
        collegeDTO.setId(college.getId());

        return collegeDTO;
    }

    @Override
    public College collegeDtoToCollege(CollegeDTO collegeDTO) {
        College college = new College();
        college.setName(collegeDTO.getName());
        college.setId(collegeDTO.getId());

        return college;
    }
}
