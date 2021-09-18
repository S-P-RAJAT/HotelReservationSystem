package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import com.bridgelabz.hotelreservationsystem.InvalidDateException.*;

public class DateServiceProvider {

    private static final String
            DATE_PATTERN = "^([0-2][0-9]|(3)[0-1])(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\d{4}$";

    public static LocalDate dateParser(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        return LocalDate.parse(date, formatter);

    }

    public static int calculateTotalDays(String startDate, String endDate) {
        return dateParser(endDate).plusDays(1).compareTo(dateParser(startDate));
    }

    public static int calculateWeekDays(String startDate, String endDate) {
        LocalDate startingDate = dateParser(startDate);
        LocalDate endingDate = dateParser(endDate);

        final DayOfWeek startDay = startingDate.getDayOfWeek();
        final DayOfWeek endDay = endingDate.plusDays(1).getDayOfWeek();

        final int days = (int) ChronoUnit.DAYS.between(startingDate, endingDate.plusDays(1));
        final int daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends
                + (startDay == DayOfWeek.SUNDAY ? 1 : 0)
                + (endDay == DayOfWeek.SUNDAY ? 1 : 0);
    }

    public static boolean validateFormatOfDate(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        return pattern.matcher(date).matches();

    }

    public static void dateValidation(String startDate, String endDate) {
        try {
            if (startDate.isEmpty() || endDate.isEmpty()) {
                throw new InvalidDateException(ExceptionType.ENTERED_EMPTY,
                        "Please enter valid date");
            }
            else if (!validateFormatOfDate(endDate) || !validateFormatOfDate(startDate)) {
                throw new InvalidDateException(ExceptionType.INVALID_DATE_FORMAT,
                        "Please enter date in valid format");
            }
            else if (dateParser(endDate).compareTo(dateParser(startDate)) < 0) {
                throw new InvalidDateException(ExceptionType.INVALID_DATES_ORDER,
                        "Enter valid date order");
            }
        } catch (NullPointerException e) {
            throw new InvalidDateException(ExceptionType.ENTERED_NULL,
                    "Please enter valid date");
        }
    }
}
