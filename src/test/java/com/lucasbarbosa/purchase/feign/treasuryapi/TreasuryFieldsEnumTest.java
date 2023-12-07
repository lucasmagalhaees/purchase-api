package com.lucasbarbosa.purchase.feign.treasuryapi;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreasuryFieldsEnumTest {

  @Test
  void testFormatFields() {
    String expected = "record_date,country,currency,country_currency_desc,exchange_rate";
    String result = TreasuryFieldsEnum.formatFields();
    Assertions.assertEquals(expected, result);
  }
}