package com.lucasbarbosa.purchase.driver.exception.custom;

public class PurchaseNotConvertedException extends BusinessException {

  public PurchaseNotConvertedException() {}

  public PurchaseNotConvertedException(String first, String second, String third) {
    super(first, second, third);
  }

  public PurchaseNotConvertedException(String first) {
    super(first);
  }
}
