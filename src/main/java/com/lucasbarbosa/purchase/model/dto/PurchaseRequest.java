package com.lucasbarbosa.purchase.model.dto;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import com.lucasbarbosa.purchase.driver.validation.DateConstraint;
import com.lucasbarbosa.purchase.driver.validation.DollarConstraint;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import java.util.function.Function;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@ApiModel(value = "PurchaseRequest", description = "Data transfer object for book registration")
public class PurchaseRequest {

  @NotNull(message = "description must not be null")
  @NotEmpty(message = "description must not be empty")
  @ApiModelProperty(value = "description", example = "Supermarket purchase", required = true)
  private String description;

  @DateConstraint
  @ApiModelProperty(value = "date of purchase", example = "dd/MM/yyyy", required = true)
  private String transactionDate;

  @DollarConstraint
  @ApiModelProperty(value = "amount of purchase", example = "100.45", required = true)
  private String purchaseAmount;

  public Purchase toDomain() {
    return assembler.apply(this);
  }

  private static final Function<PurchaseRequest, Purchase> assembler =
      dto ->
          Purchase.builder()
              .purchaseId(UUID.randomUUID().toString())
              .description(dto.getDescription())
              .transactionDate(DateUtils.parseDate(dto.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.parseAmount(dto.getPurchaseAmount()))
              .build();
}
