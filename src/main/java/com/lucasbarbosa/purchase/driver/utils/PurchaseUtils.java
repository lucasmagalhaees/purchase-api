package com.lucasbarbosa.purchase.driver.utils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUtils {

  private static final String HIFEN = "-";
  private static final String COMMA = ",";
  private static final String BLANK_SEPARATOR = " ";
  public static final int ONE = 1;
  public static final int ONE_THOUSAND = 1000;
  private static final String ENUM_ASSURANCE_MESSAGE = "field %s must be any of %s";
  private static final String SELLER_ASSURANCE_MESSAGE =
      "field license_type must be any of INDIVIDUAL, COMPANY";
  private static final String NOT_NULL_MESSAGE = "payload must not be null";
  private static final String MANDATORY_MESSAGE = "%s are mandatory";

  public static String byHifen() {
    return HIFEN;
  }

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
}
