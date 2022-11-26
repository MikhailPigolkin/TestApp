package com.example.testapp.security.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordChecker.class)
@Documented
public @interface PasswordValid {
    String message() default "Пароли не совпадают";
    Class<?>[] groups() default {};
    Class <? extends Payload >[] payload() default {};
}
