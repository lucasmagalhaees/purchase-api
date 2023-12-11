package com.lucasbarbosa.purchase.driver.exception.custom;

public class AttributeInUseException extends BusinessException {

  public AttributeInUseException(String first, String second, String third) {
    super(first, second, third);
  }

  public AttributeInUseException(String first) {
    super(first);
  }
}
