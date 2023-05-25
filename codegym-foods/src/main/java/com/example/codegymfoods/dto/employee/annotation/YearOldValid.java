package com.example.codegymfoods.dto.employee.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface YearOldValid {
    String message() default "Tuổi người dùng phải lớn hơn 16 tuổi. Vui lòng kiểm tra lại.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
