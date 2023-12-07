package com.lucasbarbosa.purchase.model.dto;

import com.lucasbarbosa.purchase.driver.validation.DateConstraint;
import com.lucasbarbosa.purchase.driver.validation.DollarConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel(value = "PurchaseRequest", description = "Data transfer object for purchase registration")
public class PurchaseRequest {

  @NotNull(message = "description must not be null")
  @NotEmpty(message = "description must not be empty")
  @ApiModelProperty(value = "description", example = "description", required = true)
  private String description;

  @DateConstraint
  @ApiModelProperty(value = "date of purchase", example = "MM/dd/yyyy", required = true)
  private String transactionDate;

  @DollarConstraint
  @ApiModelProperty(value = "amount of purchase", example = "XX.YY", required = true)
  private String purchaseAmount;
}
