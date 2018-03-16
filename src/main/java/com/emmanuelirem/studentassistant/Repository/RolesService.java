package com.emmanuelirem.studentassistant.Repository;

import com.emmanuelirem.studentassistant.Models.Security.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolesService extends CrudRepository<Roles, Long> {
}
