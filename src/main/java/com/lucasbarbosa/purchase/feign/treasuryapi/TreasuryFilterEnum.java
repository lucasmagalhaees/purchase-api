package com.lucasbarbosa.purchase.feign.treasuryapi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TreasuryFilterEnum {
  LESS_THAN("lt", "Less than"),
  LESS_THAN_OR_EQUAL_TO("lte", "Less than or equal to"),
  GREATER_THAN("gt", "Greater than"),
  GREATER_THAN_OR_EQUAL_TO("gte", "Greater than or equal to"),
  EQUAL_TO("eq", "Equal to"),
  CONTAINED_IN_A_GIVEN_SET("in", "Contained in a given set");

  private final String value;
  private final String description;

  public static String formatFilter(
      TreasuryFieldsEnum field, TreasuryFilterEnum operator, String param) {
    return String.format("%s:%s:%s", field.getValue(), operator.getValue(), param);
  }
}
