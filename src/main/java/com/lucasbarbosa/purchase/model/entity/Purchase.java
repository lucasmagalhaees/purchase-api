package com.lucasbarbosa.purchase.model.entity;

import java.time.LocalDate;
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
}
