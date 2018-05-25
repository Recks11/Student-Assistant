package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.models.v2Mapper.DepartmentDTO;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.data.DepartmentService;
import com.emmanuelirem.studentassistant.services.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Flux<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Mono<Department> findDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Flux<Program> getPrograms(String id) {
        return departmentRepository.findById(id)
                .flatMapIterable(Department::getPrograms);
    }

    @Override
    public Flux<DepartmentDTO> findAllMinimised() {
        return departmentRepository.findAll()
                .map(departmentMapper::departmentToDepartmentDTO);
    }

    @Override
    public Mono<DepartmentDTO> findMinimizedDepartmentById(String id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::departmentToDepartmentDTO);
    }

    @Override
    public Flux<Reference> getProgramsMinimised(String id) {
        return departmentRepository.findById(id)
                .flatMapIterable(
                        department -> departmentMapper
                                .departmentToDepartmentDTO(department).getProgramsReference());
    }
}
