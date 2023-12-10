package com.lucasbarbosa.purchase.driver.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

  private static final String ATTRIBUTE_IN_USE = "attribute.in.use";
  private static final String MESSAGE_NOT_READABLE = "message.not.readable";
  private static final String FEIGN_INTEGRATION = "service.not.available";
  private static final String ENTITY_NOT_FOUND = "entity.not.found";
  private static final String PURCHASE_NOT_CONVERTED = "purchase.not.converted";

  public static final int BAD_REQUEST = 400;
  public static final int NOT_FOUND = 404;

  public static final String PURCHASE = "purchase";

  public static final String PURCHASE_ID = "purchase_id";

  public static String getPurchaseNotConverted() {
    return PURCHASE_NOT_CONVERTED;
  }

  public static String getEntityNotFoundReference() {
    return ENTITY_NOT_FOUND;
  }

  public static String getAttributeInUseReference() {
    return ATTRIBUTE_IN_USE;
  }

  public static String getMessageNotReadableReference() {
    return MESSAGE_NOT_READABLE;
  }

  public static String getFeignIntegration() {
    return FEIGN_INTEGRATION;
  }

  public static Object[] buildWithSingleParam(String first) {
    return Collections.singletonList(first).toArray();
  }

  public static Object[] buildWithTwoParam(String first, String second) {
    return Arrays.asList(first, second).toArray();
  }

  public static Object[] buildWithThreeParams(String first, String second, String third) {
    return Arrays.asList(first, second, third).toArray();
  }

  public static String retrieveExceptionClassName(Exception exception) {
    return Optional.ofNullable(exception)
        .map(Exception::getClass)
        .map(Class::getSimpleName)
        .orElse(StringUtils.EMPTY);
  }
}
