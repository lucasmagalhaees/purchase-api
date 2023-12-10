package com.lucasbarbosa.purchase.driver.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

class DateValidatorTest {

  @Test
  void testValidDate() {
    DateValidator validator = new DateValidator();
    ConstraintValidatorContext context = null;
    boolean isValid = validator.isValid("01/01/2022", context);
    assertThat(isValid).isTrue();
  }

  @Test
  void testInvalidFutureDate() {
    ConstraintValidatorContext context = null;
    DateValidator validator = new DateValidator();
    boolean isValid = validator.isValid("2023-01-01", context);
    assertThat(isValid).isFalse();
  }

  @Test
  void testInvalidDateBefore2000() {
    DateValidator validator = new DateValidator();
    ConstraintValidatorContext context = null;
    boolean isValid = validator.isValid("01/01/1999", context);
    assertThat(isValid).isFalse();
  }

  @Test
  void testInvalidDateString() {
    DateValidator validator = new DateValidator();
    ConstraintValidatorContext context = null;
    boolean isValid = validator.isValid("invalid-date", context);
    assertThat(isValid).isFalse();
  }
}
