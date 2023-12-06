package com.lucasbarbosa.purchase.service.impl;

import static com.lucasbarbosa.purchase.driver.utils.ExceptionUtils.PURCHASE;
import static com.lucasbarbosa.purchase.driver.utils.ExceptionUtils.PURCHASE_ID;

import com.lucasbarbosa.purchase.driver.exception.custom.EntityNotFoundException;
import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryService;
import com.lucasbarbosa.purchase.model.dto.ConvertedPurchase;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.repository.PurchaseRepository;
import com.lucasbarbosa.purchase.service.PurchaseService;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private final PurchaseRepository purchaseRepository;

  private final TreasuryService treasuryService;

  public PurchaseServiceImpl(
      PurchaseRepository purchaseRepository, TreasuryService treasuryService) {
    this.purchaseRepository = purchaseRepository;
    this.treasuryService = treasuryService;
  }

  @Override
  public Purchase findPurchase(String purchaseId) {
    return purchaseRepository
        .findById(purchaseId)
        .orElseThrow(
            () -> {
              throw new EntityNotFoundException(PURCHASE, PURCHASE_ID, purchaseId);
            });
  }

  public PurchaseResponse savePurchase(PurchaseRequest purchaseRequest) {
    Purchase purchase = this.purchaseRepository.save(toDomain(purchaseRequest));
    return toResponse(purchase);
  }

  public Purchase toDomain(PurchaseRequest purchaseRequest) {
    return toDomain.apply(purchaseRequest);
  }

  public PurchaseResponse toResponse(Purchase purchase) {
    return toReponse.apply(purchase);
  }

  @Override
  public ConvertedPurchase retrieveConvertedPurchase(
      String purchaseId, String currencyName, String fields, String filter, String sort) {
    Purchase purchase = findPurchase(purchaseId);
    //    List<TreasuryVO> rates = treasuryService.getExchangeRates()
    return new ConvertedPurchase();
  }

  private static final Function<PurchaseRequest, Purchase> toDomain =
      dto ->
          Purchase.builder()
              .purchaseId(UUID.randomUUID().toString())
              .description(dto.getDescription())
              .transactionDate(DateUtils.parseDate(dto.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.parseAmount(dto.getPurchaseAmount()))
              .build();

  private static final Function<Purchase, PurchaseResponse> toReponse =
      model ->
          PurchaseResponse.builder()
              .purchaseId(model.getPurchaseId())
              .description(model.getDescription())
              .transactionDate(DateUtils.formatDate(model.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.centsToDollars(model.getPurchaseAmount()))
              .build();
}
