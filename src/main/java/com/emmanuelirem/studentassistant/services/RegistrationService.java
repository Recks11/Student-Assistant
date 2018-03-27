package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Student;

public interface RegistrationService {

    void registerStudent(Student student);

    void registerLecturerfromStudent(Student student);
}
