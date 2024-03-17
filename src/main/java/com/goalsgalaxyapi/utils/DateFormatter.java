package com.goalsgalaxyapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateFormatter {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime stringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDateTime format(LocalDateTime date) {
        String formattedDateTime = date.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    public static String localDateTimeToString(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(formatter);
    }
}
