package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.security.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UsersService extends CrudRepository<Users, Long>, Repository<Users, Long> {

    Users findByRegistrationNumber(String regNumber);
}
