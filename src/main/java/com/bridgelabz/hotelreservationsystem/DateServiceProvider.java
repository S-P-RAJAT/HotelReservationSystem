package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateServiceProvider {

    public static int calculateTotalDays(LocalDate startDate, LocalDate endDate) {
        return endDate.plusDays(1).compareTo(startDate);
    }
    public static long calculateWeekDays(LocalDate startDate, LocalDate endDate) {

        final DayOfWeek startDay = startDate.getDayOfWeek();
        final DayOfWeek endDay = endDate.plusDays(1).getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(startDate, endDate);
        final long daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startDay == DayOfWeek.SUNDAY ? 1 : 0) + (endDay == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
