package com.lucasbarbosa.purchase.service.impl;

import static com.lucasbarbosa.purchase.driver.utils.ExceptionUtils.PURCHASE;
import static com.lucasbarbosa.purchase.driver.utils.ExceptionUtils.PURCHASE_ID;
import static com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryFieldsEnum.RECORD_DATE;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.lucasbarbosa.purchase.driver.exception.custom.EntityNotFoundException;
import com.lucasbarbosa.purchase.driver.exception.custom.PurchaseNotConvertedException;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryFieldsEnum;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryFilterEnum;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryService;
import com.lucasbarbosa.purchase.model.dto.ConvertedPurchase;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.repository.PurchaseRepository;
import com.lucasbarbosa.purchase.service.PurchaseService;
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
    Purchase purchase = this.purchaseRepository.save(new Purchase(purchaseRequest));
    return new PurchaseResponse(purchase);
  }

  @Override
  public ConvertedPurchase retrieveConvertedPurchase(String purchaseId, String currencyName) {
    Purchase purchase = findPurchase(purchaseId);
    return treasuryService
        .getExchangeRates(
            TreasuryFieldsEnum.formatFields(),
            TreasuryFilterEnum.formatFilter(
                RECORD_DATE,
                TreasuryFilterEnum.GREATER_THAN_OR_EQUAL_TO,
                purchase.getTransactionDate().minusMonths(6).toString()),
            TreasuryFieldsEnum.formatSorting(RECORD_DATE))
        .stream()
        .filter(rate -> containsIgnoreCase(rate.getCountryCurrencyDesc(), currencyName))
        .map(rate -> new ConvertedPurchase(purchase, rate))
        .findFirst()
        .orElseThrow(PurchaseNotConvertedException::new);
  }
}
