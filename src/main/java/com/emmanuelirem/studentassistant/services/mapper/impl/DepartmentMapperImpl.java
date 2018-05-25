package com.emmanuelirem.studentassistant.services.mapper.impl;

import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.models.v2Mapper.DepartmentDTO;
import com.emmanuelirem.studentassistant.services.mapper.DepartmentMapper;
import com.emmanuelirem.studentassistant.services.mapper.ReferenceCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private ReferenceCreationService<Program> referenceCreationService;

    @Override
    public Department departmentDTOtoDepartment(DepartmentDTO departmentdto) {
        Department department = new Department();
        department.setId(departmentdto.getId());
        department.setName(departmentdto.getName());
        department.setPrograms(departmentdto.getPrograms());
        department.setCollege(departmentdto.getCollege());
        return department;
    }

    @Override
    public DepartmentDTO departmentToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setPrograms(department.getPrograms());
        departmentDTO.setCollege(department.getCollege());
        department.getPrograms().forEach(program -> {
            departmentDTO.addReference(referenceCreationService.createReference(program));
        });
        return departmentDTO;
    }
}
