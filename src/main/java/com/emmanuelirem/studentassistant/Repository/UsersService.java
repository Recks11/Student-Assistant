package com.emmanuelirem.studentassistant.Repository;

import com.emmanuelirem.studentassistant.Models.Security.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersService extends CrudRepository<Users, Long> {
}
