package com.lucasbarbosa.purchase.service;

import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PurchaseServiceTestSupport {

  @Mock protected PurchaseRepository repository;

  protected PurchaseService service;

  protected Purchase purchase;

  protected PurchaseResponse purchaseResponse;

  protected PurchaseRequest purchaseRequest;

  @BeforeEach
  void initializeTest() {
    MockitoAnnotations.initMocks(this);
    service = new PurchaseService(repository);
  }

  protected void setUpTestProbes(
      Purchase purchase, PurchaseRequest purchaseRequest, PurchaseResponse purchaseResponse) {
    this.purchase = purchase;
    this.purchaseRequest = purchaseRequest;
    this.purchaseResponse = purchaseResponse;
  }
}
