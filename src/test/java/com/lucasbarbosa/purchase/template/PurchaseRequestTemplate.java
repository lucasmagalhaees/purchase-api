package com.lucasbarbosa.purchase.template;

import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;

public class PurchaseRequestTemplate {

  public static PurchaseRequest buildWithAmount(String amount) {
    return PurchaseRequest.builder()
        .purchaseAmount(amount)
        .description("The amount to purchase")
        .transactionDate("10/02/2020")
        .build();
  }

  public static PurchaseRequest buildDefault() {
    return PurchaseRequest.builder()
        .purchaseAmount("10.45")
        .description("The amount to purchase")
        .transactionDate("10/02/2020")
        .build();
  }

  public static PurchaseRequest buildWithWrongDateFormatAndZeroAmount() {
    return PurchaseRequest.builder()
        .purchaseAmount("0")
        .description("The amount to purchase")
        .transactionDate("2020-10-02")
        .build();
  }

  public static PurchaseRequest buildWithNullDescriptionAndWrongAmountFormat() {
    return PurchaseRequest.builder()
        .purchaseAmount("0,50")
        .description(null)
        .transactionDate("2020-10-02")
        .build();
  }

  public static PurchaseRequest buildWithEmptyDescriptionAndFutureDate() {
    return PurchaseRequest.builder()
        .purchaseAmount("0.524")
        .description("")
        .transactionDate("02/10/2025")
        .build();
  }
}
