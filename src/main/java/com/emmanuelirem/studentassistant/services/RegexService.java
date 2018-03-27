package com.emmanuelirem.studentassistant.services;

public interface RegexService {

    boolean compileAndCompare(String regex, String comparatorString);

    boolean matchesStudentRegNumber(String regNumber);

    boolean matchesLecturerId(String Id);
}
