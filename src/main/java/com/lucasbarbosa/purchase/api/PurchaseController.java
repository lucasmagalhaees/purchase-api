package com.lucasbarbosa.purchase.api;

import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryFieldsEnum;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryFilterEnum;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryService;
import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryVO;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Api(tags = "PurchaseController")
public class PurchaseController {

  private final PurchaseService purchaseService;
  private final TreasuryService treasuryService;

  public PurchaseController(PurchaseService purchaseService, TreasuryService treasuryService) {
    this.purchaseService = purchaseService;
    this.treasuryService = treasuryService;
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

  @GetMapping
  @ApiOperation(value = "Resource responsible for registering a purchase")
  @ApiResponses(value = {})
  public ResponseEntity<List<TreasuryVO>> test(@PathVariable String purchaseId) {

    var teste =
        treasuryService.getExchangeRates(
            TreasuryFieldsEnum.formatFields(),
            TreasuryFilterEnum.formatFilter(
                TreasuryFieldsEnum.RECORD_DATE,
                TreasuryFilterEnum.GREATER_THAN_OR_EQUAL_TO,
                "2020-05-03"),
            "");

    return ResponseEntity.status(HttpStatus.OK).body(teste);
  }
}
