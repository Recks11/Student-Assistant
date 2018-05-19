package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.university.College;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CollegeRepository extends ReactiveMongoRepository<College, String> {
}
