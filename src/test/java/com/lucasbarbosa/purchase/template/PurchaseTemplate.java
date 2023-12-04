package com.lucasbarbosa.purchase.template;

import com.lucasbarbosa.purchase.model.entity.Purchase;
import java.time.LocalDate;

public class PurchaseTemplate {

  public static Purchase buildDefault() {
    return Purchase.builder()
        .purchaseAmount(1045L)
        .description("The amount to purchase")
        .transactionDate(LocalDate.of(2020, 10, 2))
        .build();
  }

  public static Purchase buildWithPurchaseId() {
    return Purchase.builder()
        .purchaseId("bb56515d-f215-4af2-b96d-3c8d9b0aebca")
        .purchaseAmount(1045L)
        .description("The amount to purchase")
        .transactionDate(LocalDate.of(2020, 10, 2))
        .build();
  }
}
