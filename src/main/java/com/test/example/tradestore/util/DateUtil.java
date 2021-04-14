package com.test.example.tradestore.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.util.StringUtils;

public class DateUtil {

  public static LocalDate getDate(String dateString, String dateFormat) {
    if (!StringUtils.isEmpty(dateString)) {
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(dateString, formatter);
      } catch (DateTimeParseException ex) {
        return null;
      }
    }
    return null;
  }


}
