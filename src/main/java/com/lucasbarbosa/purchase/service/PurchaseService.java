package com.lucasbarbosa.purchase.service;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.repository.PurchaseRepository;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public PurchaseResponse savePurchase(PurchaseRequest purchaseRequest) {
    Purchase purchase = this.purchaseRepository.save(toDomain(purchaseRequest));
    return toResponse(purchase);
  }

  public Purchase toDomain(PurchaseRequest purchaseRequest) {
    return toDomain.apply(purchaseRequest);
  }

  private static final Function<PurchaseRequest, Purchase> toDomain =
      dto ->
          Purchase.builder()
              .purchaseId(UUID.randomUUID().toString())
              .description(dto.getDescription())
              .transactionDate(DateUtils.parseDate(dto.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.parseAmount(dto.getPurchaseAmount()))
              .build();

  public PurchaseResponse toResponse(Purchase purchase) {
    return toReponse.apply(purchase);
  }

  private static final Function<Purchase, PurchaseResponse> toReponse =
      model ->
          PurchaseResponse.builder()
              .purchaseId(model.getPurchaseId())
              .description(model.getDescription())
              .transactionDate(DateUtils.formatDate(model.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.centsToDollars(model.getPurchaseAmount()))
              .build();
}
