package com.lucasbarbosa.purchase.driver.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  private static final String DATE_PATTERN = "MM/dd/yyyy";

  public static LocalDate parseDate(String time) {
    return LocalDate.parse(time, DateTimeFormatter.ofPattern(DATE_PATTERN));
  }

  public static String formatDate(LocalDate time) {
    return Objects.nonNull(time)
        ? time.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        : StringUtils.EMPTY;
  }
}
