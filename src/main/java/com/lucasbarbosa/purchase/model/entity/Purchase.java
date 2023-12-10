package com.lucasbarbosa.purchase.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasbarbosa.purchase.driver.utils.DateUtils;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.entity.impl.DollarImpl;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@Table(name = "purchases")
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

  @JsonIgnore @Transient private CurrencyStrategy currencyStrategy;

  public Purchase() {
    this.currencyStrategy = new DollarImpl();
  }

  public Purchase(PurchaseRequest purchaseRequest) {
    this.purchaseId = UUID.randomUUID().toString();
    this.description = purchaseRequest.getDescription();
    this.transactionDate = DateUtils.parseDate(purchaseRequest.getTransactionDate());
    this.currencyStrategy = new DollarImpl();
    this.purchaseAmount =
        this.currencyStrategy.parseAmountInCents(purchaseRequest.getPurchaseAmount());
  }
}
