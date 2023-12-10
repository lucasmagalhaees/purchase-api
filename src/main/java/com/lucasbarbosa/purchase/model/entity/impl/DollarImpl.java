package com.lucasbarbosa.purchase.model.entity.impl;

import com.lucasbarbosa.purchase.model.entity.CurrencyStrategy;
import java.math.BigDecimal;
import java.util.Locale;

public class DollarImpl implements CurrencyStrategy {

  @Override
  public Locale getLocale() {
    return new Locale("en", "US");
  }

  @Override
  public BigDecimal getIncrement() {
    return new BigDecimal("0.01");
  }

  @Override
  public String getDescription() {
    return "Dollar";
  }

  @Override
  public String getSymbol() {
    return "USD";
  }

  public BigDecimal parseAmountInDollars(String value) {
    return round(new BigDecimal(value));
  }

  public Long parseAmountInCents(String value) {
    return dollarsToCents(round(new BigDecimal(value)));
  }

  public Long dollarsToCents(BigDecimal decimal) {
    return decimal.movePointRight(getFractionalDigits()).longValueExact();
  }

  public BigDecimal centsToDollars(Long cents) {
    return new BigDecimal(cents).movePointLeft(getFractionalDigits());
  }
}
