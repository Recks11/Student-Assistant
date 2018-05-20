package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.repository.DepartmentRepository;
import com.emmanuelirem.studentassistant.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Flux<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Mono<Department> findDepartmentById(String id) {
        return departmentRepository.findById(id);
    }
}
