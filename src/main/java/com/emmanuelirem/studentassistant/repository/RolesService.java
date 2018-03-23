package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.security.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolesService extends CrudRepository<Roles, Long> {
}
