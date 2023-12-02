package com.lucasbarbosa.purchase;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas Barbosa on 31/10/2021
 */
@Configuration
@ComponentScan(basePackages = {"com.lucasbarbosa.purchase.api"})
public class ContractTestConfiguration {

  //  @MockBean private BookController bookController;
  //
  //  @MockBean private BookService bookService;
  //
  //  @MockBean private PurchaseService purchaseService;
  //
  //  @MockBean private PurchaseController purchaseController;
  //
  //  @MockBean private SellerController sellerController;
  //
  //  @MockBean private SellerService sellerService;
}
