package com.emmanuelirem.studentassistant.models.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UserIdValidator implements ConstraintValidator<MatchesIdPattern, String> {

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {

        return (contactField.matches("\\A(([0][7-9])|[1][1-9])([a-z|A-Z]{2})([0-9]{6})\\z") || contactField.matches("\\A(cu)\\d{5}\\z"));
    }


}
