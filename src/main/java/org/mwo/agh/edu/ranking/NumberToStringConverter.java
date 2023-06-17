package org.mwo.agh.edu.ranking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class NumberToStringConverter {

    private final Map<Integer, String> monthToStringMap;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");

    public NumberToStringConverter() {
        monthToStringMap = new HashMap<>();
        monthToStringMap.put(1, "Styczen");
        monthToStringMap.put(2, "Luty");
        monthToStringMap.put(3, "Marzec");
        monthToStringMap.put(4, "Kwiecien");
        monthToStringMap.put(5, "Maj");
        monthToStringMap.put(6, "Czerwiec");
        monthToStringMap.put(7, "Lipiec");
        monthToStringMap.put(8, "Sierpien");
        monthToStringMap.put(9, "Wrzesien");
        monthToStringMap.put(10, "Pazdziernik");
        monthToStringMap.put(11, "Listopad");
        monthToStringMap.put(12, "Grudzien");
    }

    protected String intToStringMonth(String value) {
        int monthValue = Integer.parseInt(value);
        return monthToStringMap.getOrDefault(monthValue, "");
    }

    public String stringToDate(String date) {
        String[] parts = date.split(" ");
        if (parts.length == 3) {
            int day = Integer.parseInt(parts[0]);
            int month = getMonthValue(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return formatter.format(LocalDate.of(year, month, day));
        } else if (parts.length == 2) {
            int month = getMonthValue(parts[0]);
            int year = Integer.parseInt(parts[1]);
            return formatter2.format(LocalDate.of(year, month, 1));
        } else throw new IllegalArgumentException("Invalid date format: " + date);
    }

    private int getMonthValue(String monthString) {
        for (Map.Entry<Integer, String> entry : monthToStringMap.entrySet())
            if (entry.getValue().equalsIgnoreCase(monthString)) return entry.getKey();
        throw new IllegalArgumentException("Invalid month: " + monthString);
    }
}