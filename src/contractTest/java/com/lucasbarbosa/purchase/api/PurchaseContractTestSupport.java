package com.lucasbarbosa.purchase.api;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

public class PurchaseContractTestSupport {

  @Autowired private MockMvc mockMvc;

  protected String json;
  protected String PURCHASE_API = "/transaction";
  protected String DOLAR_CONSTRAINT_ERROR_MESSAGE =
      "Please provide a valid amount in the format YY.YY";
  protected String DATE_CONSTRAINT_ERROR_MESSAGE =
      "Please provide a valid date in the format dd/MM/yyyy";
  protected String NULLED_DESCRIPTION_ERROR_MESSAGE = "description must not be null";
  protected String EMPTY_DESCRIPTION_ERROR_MESSAGE = "description must not be empty";
  protected String CONTENT_TYPE = "application/json;charset=UTF-8";

  protected void writeAsJson(Object object) throws JsonProcessingException {
    this.json = new ObjectMapper().writeValueAsString(object);
  }

  protected void onPurchaseRegisterFailValidateMultipleMessages(
      String firstValidationMessage, String secondValidationMessage) throws Exception {
    ResultActions result =
        mockMvc.perform(
            post(PURCHASE_API)
                .contentType(CONTENT_TYPE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON));

    result.andDo(print()).andExpect(status().isBadRequest());

    String exceptionMessage =
        Optional.of(result.andReturn())
            .map(MvcResult::getResolvedException)
            .map(Exception::getMessage)
            .orElse(EMPTY);
    assertTrue(
        Stream.of(firstValidationMessage, secondValidationMessage)
            .allMatch(exceptionMessage::contains));
  }
}
