package com.lucasbarbosa.purchase.driver.validation;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateConstraint, String> {

  @Override
  public void initialize(DateConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      LocalDate date = DateUtils.parseDate(value);
      return date.isBefore(LocalDate.now()) && date.getYear() > 2000;
    } catch (Exception e) {
      return false;
    }
  }
}
