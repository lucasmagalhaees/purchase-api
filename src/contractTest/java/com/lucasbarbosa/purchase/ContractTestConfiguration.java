package com.lucasbarbosa.purchase;

import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryService;
import com.lucasbarbosa.purchase.service.PurchaseService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.lucasbarbosa.purchase.api"})
public class ContractTestConfiguration {

  @MockBean private PurchaseService purchaseService;

  @MockBean private TreasuryService treasuryService;
}
