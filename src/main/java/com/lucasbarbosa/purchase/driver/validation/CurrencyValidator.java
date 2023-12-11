package com.lucasbarbosa.purchase.driver.validation;

import com.lucasbarbosa.purchase.model.entity.CurrencyStrategy;
import com.lucasbarbosa.purchase.model.entity.impl.DollarImpl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {

  @Override
  public void initialize(CurrencyConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      CurrencyStrategy currencyStrategy = new DollarImpl();
      return currencyStrategy.parseAmountInCents(value) > 0;
    } catch (Exception e) {
      return false;
    }
  }
}
