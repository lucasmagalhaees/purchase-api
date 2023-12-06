package com.lucasbarbosa.purchase;

import com.lucasbarbosa.purchase.api.PurchaseController;
import com.lucasbarbosa.purchase.service.impl.PurchaseServiceImpl;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.lucasbarbosa.purchase.api"})
public class ContractTestConfiguration {

  @MockBean private PurchaseController purchaseController;

  @MockBean private PurchaseServiceImpl purchaseService;
}
