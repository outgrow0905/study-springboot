package com.springboot.java.ch8;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

public class DateTest {
    @Test
    void immutable() {
        // mutable
        Calendar myBirthDay1 = new GregorianCalendar(1990, 8, 5);
        System.out.println(myBirthDay1);
        myBirthDay1.add(Calendar.MONTH, 1);
        System.out.println(myBirthDay1);

        // immutable
        LocalDate myBirthDay2 = LocalDate.of(1990, Month.SEPTEMBER ,5);
        System.out.println(myBirthDay2);
        myBirthDay2.plusMonths(1);
        System.out.println(myBirthDay2);
    }

    @Test
    void localDateTime() {
        // Instant
        Instant now1 = Instant.now();
        System.out.println(now1); // 2023-04-24T22:06:35.675183Z

        // ZoneDateTime
        ZonedDateTime now2 = ZonedDateTime.now();
        System.out.println(now2); // 2023-04-25T07:06:35.684582+09:00[Asia/Seoul]

        // LocalDateTime
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(now3); // 2023-04-25T07:06:35.684736
    }

    @Test
    void period() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = LocalDate.now().minusDays(7);

        Period period1 = Period.between(oneWeekAgo, today);
        System.out.println(period1.getDays()); // 7

        Stream<LocalDate> until1 = oneWeekAgo.datesUntil(today);
        until1.forEach(System.out::println); // 2023-04-19, 2023-04-20, 2023-04-21, 2023-04-22, 2023-04-23, 2023-04-24, 2023-04-25

        Stream<LocalDate> until2 = oneWeekAgo.datesUntil(today, Period.ofDays(3));
        until2.forEach(System.out::println); // 2023-04-19, 2023-04-22, 2023-04-25
    }

    @Test
    void duration() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1).plusHours(1);

        Duration duration = Duration.between(yesterday, today);
        System.out.println(duration.toDays()); // 0
    }
}
