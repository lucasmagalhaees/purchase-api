package com.lucasbarbosa.purchase.driver.utils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.lucasbarbosa.purchase.feign.IntegrationParamEnum;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author Lucas Barbosa on 27/06/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUtils {

  private static final String COMMA = ", ";
  private static final String BLANK_SEPARATOR = " ";
  public static final int ONE = 1;
  public static final int ONE_THOUSAND = 1000;
  private static final String ENUM_ASSURANCE_MESSAGE = "field %s must be any of %s";
  private static final String SELLER_ASSURANCE_MESSAGE =
      "field license_type must be any of INDIVIDUAL, COMPANY";
  private static final String NOT_NULL_MESSAGE = "payload must not be null";
  private static final String MANDATORY_MESSAGE = "%s are mandatory";

  public static String byComma() {
    return COMMA;
  }

  public static String byBlankSeparator() {
    return BLANK_SEPARATOR;
  }

  public static String[] createEmptyStringArray() {
    return new String[] {};
  }

  public static String convertObjectToString(Object object) {
    return Optional.ofNullable(object).map(Objects::toString).orElse(EMPTY);
  }

  public static List<String> convertEnumToStringList(Class<? extends Enum<?>> enumeration) {
    return Stream.of(enumeration.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
  }

  public static String joinStringListByComma(List<String> stringList) {
    return String.join(COMMA, stringList);
  }

  public static String generateSpecificationQueryPattern(String string) {

    return "%" + string.toUpperCase() + "%";
  }

  public static Long parseAmount(String value) {
    BigDecimal increment = new BigDecimal("0.01");
    BigDecimal divided = new BigDecimal(value).divide(increment, 0, RoundingMode.HALF_EVEN);
    return dollarsToCents(divided.multiply(increment));
  }

  public static BigDecimal round(BigDecimal value) {
    BigDecimal increment = new BigDecimal("0.01");
    BigDecimal divided = value.divide(increment, 0, RoundingMode.UP);
    return divided.multiply(increment);
  }

  public static Long dollarsToCents(BigDecimal usd) {
    Locale usLocale = new Locale("en", "US"); // United States
    Currency usCurrency = Currency.getInstance(usLocale);
    int usNumFractionalDigits = usCurrency.getDefaultFractionDigits();
    return usd.movePointRight(usNumFractionalDigits).longValueExact();
  }

  public static BigDecimal centsToDollars(Long cents) {
    BigDecimal bd = new BigDecimal(cents);
    Locale usLocale = new Locale("en", "US"); // United States
    Currency usCurrency = Currency.getInstance(usLocale);
    int usNumFractionalDigits = usCurrency.getDefaultFractionDigits();
    return bd.movePointLeft(usNumFractionalDigits);
  }

  public static boolean hasNulls(List<Object> objects) {
    return objects.stream().allMatch((Predicate.not(ObjectUtils::isEmpty)));
  }

  public static String handleCustomerCpf(String cpf) {

    return cpf.replace(".", EMPTY).replace("-", EMPTY);
  }

  public static String handleCustomerCnpj(String cnpj) {
    return cnpj.replace(".", EMPTY).replace("-", EMPTY).replace("/", EMPTY);
  }

  public static boolean areAllPresent(List<Optional<?>> optionalList) {
    return optionalList.stream().allMatch(Optional::isPresent);
  }

  public static String searchMapByParam(
      Optional<Map<String, Object>> params, IntegrationParamEnum key) {
    return params
        .map(mapParams -> mapParams.get(key.getValue()).toString())
        .orElse(StringUtils.EMPTY);
  }
}
