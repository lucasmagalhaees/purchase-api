package com.lucasbarbosa.purchase.api;

import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas Barbosa on 27/06/2021
 */
@RestController
@RequestMapping
@Api(tags = "Any")
public class PurchaseController {

  @PostMapping
  @ApiOperation(value = "Resource responsible for registering a list of books")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Book sucessfully registered"),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
      })
  public ResponseEntity<PurchaseResponse> createListOfBooks(
      @Validated @RequestBody PurchaseRequest purchaseRequest) {
    var teste = purchaseRequest.toDomain();
    return ResponseEntity.status(HttpStatus.OK).body(teste.toReponse());
  }
}
