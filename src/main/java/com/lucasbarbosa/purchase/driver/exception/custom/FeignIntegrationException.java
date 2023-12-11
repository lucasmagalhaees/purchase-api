package com.lucasbarbosa.purchase.driver.exception.custom;

public class FeignIntegrationException extends BusinessException {

  public FeignIntegrationException(String first) {
    super(first);
  }
}
