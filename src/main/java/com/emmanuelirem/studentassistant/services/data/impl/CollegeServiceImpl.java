package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.v2Mapper.CollegeDTO;
import com.emmanuelirem.studentassistant.repository.CollegeRepository;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.data.CollegeService;
import com.emmanuelirem.studentassistant.services.mapper.CollegeMapper;
import com.emmanuelirem.studentassistant.services.mapper.ReferenceCreationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final CollegeMapper collegeMapper;
    private final ReferenceCreationService<Department> referenceCreationService;

    public CollegeServiceImpl(CollegeRepository collegeRepository, DepartmentRepository departmentRepository, CollegeMapper collegeMapper, ReferenceCreationService<Department> referenceCreationService) {
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
        this.collegeMapper = collegeMapper;
        this.referenceCreationService = referenceCreationService;
    }

    @Override
    public Flux<College> findAll() {
        return collegeRepository.findAll();
    }

    @Override
    public Flux<CollegeDTO> findAllColleges() {

        return collegeRepository.findAll()
                .flatMap(college -> {
                    CollegeDTO collegeDTO = collegeMapper.collegeToCollegeDTO(college);
                    return departmentRepository.findDepartmentsByCollege_Id(collegeDTO.getId())
                            .map(department -> {
                                collegeDTO.addReference(referenceCreationService.createReference(department));
                                return collegeDTO;
                            });
                });
    }
}
