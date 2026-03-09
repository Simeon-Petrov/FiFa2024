package com.sirma.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("M/d/yyyy"),    // 6/14/2024
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),  // 06/14/2024
            DateTimeFormatter.ofPattern("MM dd yyyy"),  // 06 14 2024
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),  // 2024/06/14
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),  // 2024-06-14
            DateTimeFormatter.ofPattern("yyyy MM dd"),  // 2024 06 14
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),  // 14-06-2024
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),  // 14.06.2024
            DateTimeFormatter.ofPattern("d.M.yyyy"),    // 4.6.2024
            DateTimeFormatter.ofPattern("d M yyyy"),   // 4 06 2024
            DateTimeFormatter.ofPattern("dd MM yyyy"),  // 14 06 2024
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),  // 14/06/2024
            DateTimeFormatter.ofPattern("d/MM/yyyy")    // 4/06/2024
    );

    public static LocalDate parse(String dateStr) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(dateStr.trim(), formatter);
            } catch (DateTimeParseException e) {

            }
        }
        System.out.println("Unknown date format: " + dateStr);
        return null;
    }

    public static String parseToString(String dateStr) {
        LocalDate date = parse(dateStr);
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return dateStr;
    }
}
