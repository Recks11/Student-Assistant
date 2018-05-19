package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Student;
import reactor.core.publisher.Mono;

public interface RegistrationService {

    Mono<Void> registerStudent(Student student);

    Mono<Void> registerLecturerfromStudent(Student student);
}
