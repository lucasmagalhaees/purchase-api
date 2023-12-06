package com.lucasbarbosa.purchase.service;

import com.lucasbarbosa.purchase.model.dto.ConvertedPurchase;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;

public interface PurchaseService {

  public Purchase findPurchase(String purchaseId);

  public PurchaseResponse savePurchase(PurchaseRequest purchaseRequest);

  public Purchase toDomain(PurchaseRequest purchaseRequest);

  public PurchaseResponse toResponse(Purchase purchase);

  public ConvertedPurchase retrieveConvertedPurchase(
      String purchaseId, String currencyName, String fields, String filter, String sort);
}
