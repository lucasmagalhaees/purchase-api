package com.lucasbarbosa.purchase.driver.exception.custom;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException(String first, String second, String third) {
    super(first, second, third);
  }

  public EntityNotFoundException(String first) {
    super(first);
  }
}
