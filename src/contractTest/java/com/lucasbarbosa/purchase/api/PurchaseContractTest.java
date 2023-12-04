package com.lucasbarbosa.purchase.api;

import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithNullDescriptionAndWrongAmountFormat;
import static com.lucasbarbosa.purchase.template.PurchaseRequestTemplate.buildWithWrongDateFormatAndZeroAmount;

import com.lucasbarbosa.purchase.ContractTestConfiguration;
import com.lucasbarbosa.purchase.template.PurchaseRequestTemplate;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {ContractTestConfiguration.class})
@RunWith(SpringRunner.class)
@WebMvcTest
public class PurchaseContractTest extends PurchaseContractTestSupport {

  @Test
  @DisplayName(
      "Given unsuitable date value and zero amount, then return BAD REQUEST with field validation message")
  public void givenUnsuitableDateFormatAndZeroAmountThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithWrongDateFormatAndZeroAmount());

    onPurchaseRegisterFailValidateMultipleMessages(
        DOLAR_CONSTRAINT_ERROR_MESSAGE, DATE_CONSTRAINT_ERROR_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given null description and wrong amount format, then return BAD REQUEST with field validation message")
  public void givenNullDescriptionAndWrongAmountFormatThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithNullDescriptionAndWrongAmountFormat());

    onPurchaseRegisterFailValidateMultipleMessages(
        DOLAR_CONSTRAINT_ERROR_MESSAGE, NULLED_DESCRIPTION_ERROR_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given future date and empty description, then return BAD REQUEST with field validation message")
  public void givenEmptyDescriptionAndFutureDateThenReturnBadRequest() throws Exception {

    writeAsJson(PurchaseRequestTemplate.buildWithEmptyDescriptionAndFutureDate());

    onPurchaseRegisterFailValidateMultipleMessages(
        EMPTY_DESCRIPTION_ERROR_MESSAGE, DATE_CONSTRAINT_ERROR_MESSAGE);
  }
}