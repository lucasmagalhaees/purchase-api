package com.lucasbarbosa.purchase.api;

import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithNullDescriptionAndWrongAmountFormat;
import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithWrongDateFormatAndZeroAmount;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucasbarbosa.purchase.model.dto.PurchaseRequest;
import com.lucasbarbosa.purchase.model.dto.PurchaseResponse;
import com.lucasbarbosa.purchase.template.PurchaseRequestTemplate;
import com.lucasbarbosa.purchase.template.PurchaseResponseTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PurchaseController.class)
@AutoConfigureMockMvc
public class PurchaseContractTest extends PurchaseContractTestSupport {

  @Test
  public void givenUnsuitableDateFormatAndZeroAmountThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithWrongDateFormatAndZeroAmount());

    onPurchaseRegisterFailValidateMultipleMessages(
        DOLAR_CONSTRAINT_ERROR_MESSAGE, DATE_CONSTRAINT_ERROR_MESSAGE);
  }

  @Test
  public void givenSuitableRequestItShouldRegisterPurchase() throws Exception {
    // Arrange
    PurchaseRequest request = PurchaseRequestTemplate.buildDefault();
    PurchaseResponse expectedResponse = PurchaseResponseTemplate.buildDefault();

    when(purchaseService.savePurchase(any(PurchaseRequest.class))).thenReturn(expectedResponse);

    writeAsJson(request);

    // Act
    ResultActions result =
        mockMvc.perform(
            MockMvcRequestBuilders.post(PURCHASE_API)
                .contentType(CONTENT_TYPE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON));

    // Assert
    result.andExpect(status().isCreated());
    // You might want to further assert the response content or other details if needed.
  }

  @Test
  public void givenNullDescriptionAndWrongAmountFormatThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithNullDescriptionAndWrongAmountFormat());

    onPurchaseRegisterFailValidateMultipleMessages(
        DOLAR_CONSTRAINT_ERROR_MESSAGE, NULLED_DESCRIPTION_ERROR_MESSAGE);
  }

  @Test
  public void givenEmptyDescriptionAndFutureDateThenReturnBadRequest() throws Exception {

    writeAsJson(PurchaseRequestTemplate.buildWithEmptyDescriptionAndFutureDate());

    onPurchaseRegisterFailValidateMultipleMessages(
        EMPTY_DESCRIPTION_ERROR_MESSAGE, DATE_CONSTRAINT_ERROR_MESSAGE);
  }
}
