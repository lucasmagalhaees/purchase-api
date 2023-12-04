package com.lucasbarbosa.purchase.driver.validation;

import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DolarValidator implements ConstraintValidator<DollarConstraint, String> {

  @Override
  public void initialize(DollarConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      return PurchaseUtils.parseAmount(value) > 0;
    } catch (Exception e) {
      return false;
    }
  }
}
