package org.example.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  StringTool is a utility class for String modifying methods
 *
 * @version 1.0 , 12-may-2020
 */
public final class StringTool {
   //   public final static String dfPattern_MetricShort = "d-M-uu";
//   public final static String dfPattern_MetricMedium = "dd LLL uuuu";
//   public final static String dfPattern_MetricLong = "dd LLLL uuuu";
//   public final static String tfPattern_MetricShort = "HH:mm";
//   public final static String tfPattern_MetricLong = "HH:mm:ss";
   public final static String dtfPattern_MetricShort = "d-M-uu H:mm[:ss]";
   public final static String dtfPattern_MetricShort_noSeconds = "d-M-uu H:mm";
   public final static String dtfPattern_MetricMediumDashed = "dd-MM-uuuu HH:mm[:ss]";
   public final static String dtfPattern_MetricMediumSpaced = "dd LLL uuuu HH:mm[:ss]";
   public final static String dtfPattern_MetricMediumDashed_noSeconds = "dd-MM-uuuu HH:mm";
   public final static String dtfPattern_MetricMediumSpaced_noSeconds = "dd LLL uuuu HH:mm";
   public final static String dtfPattern_MetricLong = "dd LLLL uuuu HH:mm:ss";

   final static String DEFAULT_FAILED_DATETIME_RETURN_STRING = "";

   private StringTool() {
   }

   public static String dateTimeToString(LocalDateTime dateTime, String pattern, String emptyReturnValue) {
      if (dateTime == null || pattern == null || pattern.isBlank()) return emptyReturnValue;
      return dateTime.format(DateTimeFormatter.ofPattern(pattern));
   }

   public static String dateTimeToString(LocalDateTime dateTime, String pattern) {
      return dateTimeToString(dateTime, pattern, DEFAULT_FAILED_DATETIME_RETURN_STRING);
   }

   public static String dateTimeToMetricShortString(LocalDateTime dateTime) {
      return dateTimeToString(dateTime, dtfPattern_MetricShort);
   }

   public static String dateTimeToMetricMediumDashedString(LocalDateTime dateTime) {
      return dateTimeToString(dateTime,dtfPattern_MetricMediumDashed);
   }

   public static String dateTimeToMetricMediumSpacedString(LocalDateTime dateTime) {
      return dateTimeToString(dateTime, dtfPattern_MetricMediumSpaced);
   }

   public static String dateTimeToMetricShortStringNoSeconds(LocalDateTime dateTime) {
      return dateTimeToString(dateTime,dtfPattern_MetricShort_noSeconds);
   }

   public static String dateTimeToMetricMediumDashedStringNoSeconds(LocalDateTime dateTime) {
      return dateTimeToString(dateTime, dtfPattern_MetricMediumDashed_noSeconds);
   }

   public static String dateTimeToMetricMediumSpacedStringNoSeconds(LocalDateTime dateTime) {
      return dateTimeToString(dateTime,dtfPattern_MetricMediumSpaced_noSeconds);
   }

   public static String dateTimeToMetricLongString(LocalDateTime dateTime) {
      return dateTimeToString(dateTime,dtfPattern_MetricLong);
   }

   public static LocalDateTime StringToDateTime(String dateTime, String pattern) {
      if (dateTime == null || pattern == null || pattern.isBlank()) return null;
      return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
   }

   public static LocalDateTime metricShortStringToDateTime(String dateTime) {
      return StringToDateTime(dateTime, dtfPattern_MetricShort);
   }

   public static LocalDateTime metricMediumDashedStringToDateTime(String dateTime) {
      return StringToDateTime(dateTime, dtfPattern_MetricMediumDashed);
   }

   public static LocalDateTime metricMediumSpacedStringToDateTime(String dateTime) {
      return StringToDateTime(dateTime, dtfPattern_MetricMediumSpaced);
   }

   public static LocalDateTime metricLongStringToDateTime(String dateTime) {
      return StringToDateTime(dateTime, dtfPattern_MetricLong);
   }
}
