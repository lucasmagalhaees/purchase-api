package com.lucasbarbosa.purchase.feign.treasuryapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Lucas Barbosa on 31/07/2021
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TreasuryVO {

  @JsonProperty("record_date")
  private String recordDate;

  private String country;
  private String currency;

  @JsonProperty("country_currency_desc")
  private String countryCurrencyDesc;

  @JsonProperty("exchange_rate")
  private String exchangeRate;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("recordDate", recordDate)
        .append("country", country)
        .append("currency", currency)
        .append("countryCurrencyDesc", countryCurrencyDesc)
        .append("exchangeRate", exchangeRate)
        .toString();
  }
}
