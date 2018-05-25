package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.services.data.SortService;
import org.springframework.stereotype.Service;

@Service
public class SortServiceImpl implements SortService {
    @Override
    public int compare(Course o1, Course o2) {
        return o2.getUnits()-o1.getUnits();
    }
}
