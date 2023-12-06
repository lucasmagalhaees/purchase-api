package com.lucasbarbosa.purchase.service;

import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryService;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.repository.PurchaseRepository;
import com.lucasbarbosa.purchase.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PurchaseServiceTestSupport {

  @Mock protected PurchaseRepository repository;

  @Mock protected TreasuryService treasuryService;

  protected PurchaseService service;

  protected Purchase purchase;

  protected PurchaseResponse purchaseResponse;

  protected PurchaseRequest purchaseRequest;

  @BeforeEach
  void initializeTest() {
    MockitoAnnotations.initMocks(this);
    service = new PurchaseServiceImpl(repository, treasuryService);
  }

  protected void setUpTestProbes(
      Purchase purchase, PurchaseRequest purchaseRequest, PurchaseResponse purchaseResponse) {
    this.purchase = purchase;
    this.purchaseRequest = purchaseRequest;
    this.purchaseResponse = purchaseResponse;
  }
}
