package com.lucasbarbosa.purchase.feign.treasuryapi;

import com.lucasbarbosa.purchase.driver.utils.PurchaseUtils;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TreasuryFieldsEnum {
  RECORD_DATE("record_date"),
  COUNTRY("country"),
  CURRENCY("currency"),
  COUNTRY_CURRENCY_DESC("country_currency_desc"),
  EXCHANGE_RATE("exchange_rate");

  private final String value;

  public static String formatFields() {
    return Arrays.stream(values())
        .map(TreasuryFieldsEnum::getValue)
        .collect(Collectors.joining(PurchaseUtils.byComma()));
  }
}
