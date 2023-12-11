package com.lucasbarbosa.purchase.service;

import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildDefault;
import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.lucasbarbosa.purchase.driver.exception.custom.EntityNotFoundException;
import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.model.entity.Purchase;
import com.lucasbarbosa.purchase.template.PurchaseResponseTemplate;
import com.lucasbarbosa.purchase.template.PurchaseTemplate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PurchaseServiceTest extends PurchaseServiceTestSupport {

  @Test
  void itShouldFindPurchase() {
    String purchaseId = "exampleId";
    Purchase expectedPurchase = new Purchase();
    when(repository.findById(purchaseId)).thenReturn(Optional.of(expectedPurchase));
    Purchase result = service.findPurchase(purchaseId);
    assertEquals(expectedPurchase, result);
  }

  @Test
  void itShouldSavePurchase() {
    PurchaseRequest purchaseRequest = buildDefault();
    PurchaseResponse expectedResponse = PurchaseResponseTemplate.buildDefault();
    Purchase expectedPurchase = PurchaseTemplate.buildWithPurchaseId();
    when(repository.save(any(Purchase.class))).thenReturn(expectedPurchase);
    PurchaseResponse result = service.savePurchase(purchaseRequest);
    assertThat(expectedResponse).isEqualToComparingFieldByField(result);
  }

  @Test
  void itShouldEntityNotFoundExceptionWhenPurchaseIsNotFound() {
    String purchaseId = "nonexistentId";
    when(repository.findById(purchaseId)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> service.findPurchase(purchaseId));
  }

  @Test
  void itShouldRoundAmountCorrectly() {
    assertEquals(10045, new Purchase(buildWithAmount("100.448")).getPurchaseAmount().longValue());

    assertEquals(10045, new Purchase(buildWithAmount("100.452")).getPurchaseAmount().longValue());

    assertEquals(10045, new Purchase(buildWithAmount("100.450")).getPurchaseAmount().longValue());
  }

  @Test
  void itShouldProperlyConvertToDomain() {
    assertThat(new Purchase(buildDefault()))
        .isEqualToIgnoringGivenFields(
            PurchaseTemplate.buildDefault(), "purchaseId", "currencyStrategy");
  }

  @Test
  void itShouldProperlyConvertToReponse() {
    assertThat(new PurchaseResponse(PurchaseTemplate.buildDefault()))
        .isEqualToIgnoringGivenFields(PurchaseResponseTemplate.buildDefault(), "purchaseId");
  }
}
