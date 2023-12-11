package com.lucasbarbosa.purchase.model.dto;

import static com.lucasbarbosa.purchase.driver.utils.DateUtils.formatDate;

import com.lucasbarbosa.purchase.feign.treasuryapi.TreasuryVO;
import com.lucasbarbosa.purchase.model.entity.CurrencyStrategy;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedPurchase {

  private String purchaseId;

  private String description;

  private String transactionDate;

  private String recordDate;

  private BigDecimal exchangeRate;

  private String originalPurchaseAmount;

  private String originalCurrencyDescription;

  private String convertedPurchaseAmount;

  private String convertedCurrencyDescription;

  public ConvertedPurchase(Purchase purchase, TreasuryVO vo) {
    CurrencyStrategy currencyStrategy = purchase.getCurrencyStrategy();
    var originalPurchase = currencyStrategy.centsToDollars(purchase.getPurchaseAmount());
    this.purchaseId = purchase.getPurchaseId();
    this.description = purchase.getDescription();
    this.recordDate = formatDate(LocalDate.parse(vo.getRecordDate()));
    this.originalCurrencyDescription = purchase.getCurrencyStrategy().getDescription();
    this.convertedCurrencyDescription = vo.getCountryCurrencyDesc();
    this.transactionDate = formatDate(purchase.getTransactionDate());
    this.originalPurchaseAmount =
        currencyStrategy.formatCurrencyWithSymbol(originalPurchase, currencyStrategy.getSymbol());
    this.exchangeRate = purchase.getCurrencyStrategy().parseAmountInDollars(vo.getExchangeRate());
    this.convertedPurchaseAmount =
        currencyStrategy.formatCurrencyWithSymbol(
            currencyStrategy.round(this.exchangeRate.multiply(originalPurchase)), vo.getCurrency());
  }
}
