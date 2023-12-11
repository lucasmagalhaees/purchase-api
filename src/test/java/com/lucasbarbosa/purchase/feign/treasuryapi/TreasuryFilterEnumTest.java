package com.lucasbarbosa.purchase.feign.treasuryapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreasuryFilterEnumTest {

  @Test
  void testFormatFilter() {
    TreasuryFieldsEnum field = TreasuryFieldsEnum.RECORD_DATE;
    TreasuryFilterEnum operator = TreasuryFilterEnum.LESS_THAN;
    String param = "2023-01-01";
    String expected = "record_date:lt:2023-01-01";
    String result = TreasuryFilterEnum.formatFilter(field, operator, param);
    Assertions.assertEquals(expected, result);
  }
}
