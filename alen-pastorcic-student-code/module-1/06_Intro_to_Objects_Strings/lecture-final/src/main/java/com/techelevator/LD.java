package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LD {
    public static void main(String[] args) {

        // creating a LocalDate
        // Factory Method design pattern
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate endOfJan = LocalDate.of(2024, 1, 31);
        System.out.println(endOfJan);

        // comparing
//        endOfJan.compareTo()
        System.out.println(endOfJan.equals(today));
        System.out.println(endOfJan.isBefore(today));
        System.out.println(endOfJan.isAfter(today));

        // manipulating dates
        System.out.println(today.plusDays(1));
        System.out.println(today.plusWeeks(1));
        System.out.println(endOfJan.plusMonths(1));
        System.out.println(endOfJan
                .plusMonths(1)
                .minusMonths(1));

        // formatting
        System.out.println(today.format(DateTimeFormatter.ISO_DATE));
        System.out.println(today.format(DateTimeFormatter.ISO_ORDINAL_DATE));
        System.out.println(today.format(DateTimeFormatter.ofPattern("YYYY MMM dd")));
        System.out.println(today.format(DateTimeFormatter.ofPattern("YYYY MM dd")));
        System.out.println(today.format(DateTimeFormatter.ofPattern("YYYYMMdd")));

    }
}
