package com.emmanuelirem.studentassistant.services.mapper;

import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.v2Mapper.DepartmentDTO;

public interface DepartmentMapper {

    Department departmentDTOtoDepartment(DepartmentDTO department);

    DepartmentDTO departmentToDepartmentDTO(Department department);
}
