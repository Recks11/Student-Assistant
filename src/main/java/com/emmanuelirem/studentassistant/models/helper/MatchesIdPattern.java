package com.emmanuelirem.studentassistant.models.helper;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesIdPattern {
    String message() default "ID Not Valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
