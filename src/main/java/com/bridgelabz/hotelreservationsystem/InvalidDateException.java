package com.bridgelabz.hotelreservationsystem;

public class InvalidDateException extends RuntimeException {

    enum ExceptionType {
        ENTERED_NULL, ENTERED_EMPTY, INVALID_DATE_FORMAT, INVALID_DATES_ORDER
    }

    ExceptionType type;

    public InvalidDateException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
