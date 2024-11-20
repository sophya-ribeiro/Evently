package com.evently.repository.entity;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class DateUtils {
    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}