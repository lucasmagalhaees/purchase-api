package com.lucasbarbosa.purchase.driver.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

class CurrencyValidatorTest {

  @Test
  void testValidAmount() {
    // Arrange
    CurrencyValidator validator = new CurrencyValidator();
    ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    // Act
    boolean isValid = validator.isValid("100", context);

    // Assert
    assertThat(isValid).isTrue();
  }

  @Test
  void testInvalidZeroAmount() {
    // Arrange
    CurrencyValidator validator = new CurrencyValidator();
    ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    // Act
    boolean isValid = validator.isValid("0", context);

    // Assert
    assertThat(isValid).isFalse();
  }

  @Test
  void testInvalidNegativeAmount() {
    // Arrange
    CurrencyValidator validator = new CurrencyValidator();
    ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    // Act
    boolean isValid = validator.isValid("-100", context);

    // Assert
    assertThat(isValid).isFalse();
  }

  @Test
  void testInvalidNonNumericValue() {
    // Arrange
    CurrencyValidator validator = new CurrencyValidator();
    ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    // Act
    boolean isValid = validator.isValid("invalid-value", context);

    // Assert
    assertThat(isValid).isFalse();
  }
}
