package com.lucasbarbosa.purchase.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseResponse {

  private String purchaseId;

  private String description;

  private String transactionDate;

  private BigDecimal purchaseAmount;
}
