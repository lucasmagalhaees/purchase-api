package com.lucasbarbosa.purchase.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedPurchase {

  private String purchaseId;

  private String description;

  private String transactionDate;

  private BigDecimal originalPurchaseAmount;

  private BigDecimal exchangeRate;

  private BigDecimal convertedPurchaseAmount;
}
