package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.services.RegexService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexServiceImpl implements RegexService{


    @Override
    public boolean compileAndCompare(String regex, String comparatorString) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(comparatorString);

        return matcher.matches();
    }

    @Override
    public boolean matchesStudentRegNumber(String regNumber) {

        String studentRegex = "\\A(([0][7-9])|[1][1-9])([a-z|A-Z]{2})([0-9]{6})\\z";
        return compileAndCompare(studentRegex, regNumber);
    }

    @Override
    public boolean matchesLecturerId(String Id) {
        String lecturerRegex = "\\A(cu)\\d{5}\\z";
        return compileAndCompare(lecturerRegex, Id);
    }
}
