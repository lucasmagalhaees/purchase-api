package com.lucasbarbosa.purchase.template;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import java.time.LocalDate;

public class PurchaseResponseTemplate {

  public static PurchaseResponse buildDefault() {
    return PurchaseResponse.builder()
        .purchaseId("bb56515d-f215-4af2-b96d-3c8d9b0aebca")
        .purchaseAmount(PurchaseUtils.centsToDollars(1045L))
        .description("The amount to purchase")
        .transactionDate(DateUtils.formatDate(LocalDate.of(2020, 10, 2)))
        .build();
  }
}
