package com.lucasbarbosa.purchase.model.entity;

import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import java.time.LocalDate;
import java.util.function.Function;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_purchase")
public class Purchase {

  @Id
  @Column(name = "purchase_id")
  private String purchaseId;

  @Column(name = "description")
  private String description;

  @Column(name = "transaction_date")
  private LocalDate transactionDate;

  @Column(name = "purchase_amount")
  private Long purchaseAmount;

  public PurchaseResponse toReponse() {
    return assembler.apply(this);
  }

  private static final Function<Purchase, PurchaseResponse> assembler =
      model ->
          PurchaseResponse.builder()
              .purchaseId(model.getPurchaseId())
              .description(model.getDescription())
              .transactionDate(DateUtils.formatDate(model.getTransactionDate()))
              .purchaseAmount(PurchaseUtils.centsToDollars(model.getPurchaseAmount()).toString())
              .build();
}
