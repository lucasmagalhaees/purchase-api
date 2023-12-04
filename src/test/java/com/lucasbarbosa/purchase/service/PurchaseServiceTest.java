package com.lucasbarbosa.purchase.service;

import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lucasbarbosa.purchase.template.PurchaseRequestTemplate;
import com.lucasbarbosa.purchase.template.PurchaseResponseTemplate;
import com.lucasbarbosa.purchase.template.PurchaseTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class PurchaseServiceTest extends PurchaseServiceTestSupport {

  @Test
  @DisplayName("Given 3 decimal cases it should round to nearest cent")
  void itShouldRoundAmountCorrectly() {
    assertEquals(
        10045, service.toDomain(buildWithAmount("100.448")).getPurchaseAmount().longValue());

    assertEquals(
        10045, service.toDomain(buildWithAmount("100.452")).getPurchaseAmount().longValue());

    assertEquals(
        10045, service.toDomain(buildWithAmount("100.450")).getPurchaseAmount().longValue());
  }

  @Test
  @DisplayName("Given a purchase request it should convert to domain")
  void itShouldProperlyConvertToDomain() {
    assertThat(service.toDomain(PurchaseRequestTemplate.buildDefault()))
        .isEqualToIgnoringGivenFields(PurchaseTemplate.buildDefault(), "purchaseId");
  }

  @Test
  @DisplayName("Given a purchase it should convert to response")
  void itShouldProperlyConvertToReponse() {
    assertThat(service.toResponse(PurchaseTemplate.buildDefault()))
        .isEqualToIgnoringGivenFields(PurchaseResponseTemplate.buildDefault(), "purchaseId");
  }
}
