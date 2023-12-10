package com.lucasbarbosa.purchase.model.dto;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {

  private String purchaseId;

  private String description;

  private String transactionDate;

  private BigDecimal purchaseAmount;

  public PurchaseResponse(Purchase purchase) {
    this.purchaseId = purchase.getPurchaseId();
    this.description = purchase.getDescription();
    this.transactionDate = DateUtils.formatDate(purchase.getTransactionDate());
    this.purchaseAmount =
        purchase.getCurrencyStrategy().centsToDollars(purchase.getPurchaseAmount());
  }
}
