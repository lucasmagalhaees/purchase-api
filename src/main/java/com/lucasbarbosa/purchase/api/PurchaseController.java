package com.lucasbarbosa.purchase.api;

import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Api(tags = "PurchaseController")
public class PurchaseController {

  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping
  @ApiOperation(value = "Resource responsible for registering a purchase")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Purchase sucessfully registered"),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
      })
  public ResponseEntity<PurchaseResponse> registerPurchase(
      @Validated @RequestBody PurchaseRequest purchaseRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(purchaseService.savePurchase(purchaseRequest));
  }
}
