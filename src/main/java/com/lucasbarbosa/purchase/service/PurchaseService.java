package com.lucasbarbosa.purchase.service;

import com.lucasbarbosa.purchase.model.dto.ConvertedPurchase;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import org.springframework.cache.annotation.CacheEvict;

public interface PurchaseService {

  public Purchase findPurchase(String purchaseId);

  @CacheEvict(value = "currencies", allEntries = true)
  public PurchaseResponse savePurchase(PurchaseRequest purchaseRequest);

  public ConvertedPurchase retrieveConvertedPurchase(String purchaseId, String currencyName);
}
