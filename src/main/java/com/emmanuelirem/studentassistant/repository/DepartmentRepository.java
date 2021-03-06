package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, Repository<Department, Long> {

    Department findDepartmentByName(String name);
}
