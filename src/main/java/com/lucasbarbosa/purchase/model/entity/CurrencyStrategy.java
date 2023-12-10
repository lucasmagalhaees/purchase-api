package com.lucasbarbosa.purchase.model.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public interface CurrencyStrategy {

  public Locale getLocale();

  public BigDecimal getIncrement();

  public String getDescription();

  public String getSymbol();

  public BigDecimal parseAmountInDollars(String value);

  public Long parseAmountInCents(String value);

  public Long dollarsToCents(BigDecimal decimal);

  public BigDecimal centsToDollars(Long cents);

  public default BigDecimal round(BigDecimal value) {
    BigDecimal divided = value.divide(getIncrement(), 0, RoundingMode.HALF_EVEN);
    return divided.multiply(getIncrement());
  }

  public default int getFractionalDigits() {
    Currency usCurrency = Currency.getInstance(getLocale());
    return usCurrency.getDefaultFractionDigits();
  }

  public default String formatCurrencyWithSymbol(BigDecimal value, String symbol) {
    return String.format("%s %s", value.toString(), symbol);
  }
}
