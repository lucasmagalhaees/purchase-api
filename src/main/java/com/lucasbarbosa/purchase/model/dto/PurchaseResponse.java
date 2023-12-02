package com.lucasbarbosa.purchase.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseResponse {

  private String purchaseId;

  private String description;

  private String transactionDate;

  private String purchaseAmount;
}
