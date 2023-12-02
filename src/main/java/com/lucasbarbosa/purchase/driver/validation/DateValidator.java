package com.lucasbarbosa.purchase.driver.validation;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
      return date.isBefore(LocalDate.now());
    } catch (Exception e) {
      return false;
    }
  }
}
