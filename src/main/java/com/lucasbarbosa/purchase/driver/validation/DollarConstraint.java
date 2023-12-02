package com.lucasbarbosa.purchase.driver.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DolarValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DollarConstraint {
  String message() default "Invalid currency format. The valid format is XX.XX";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
