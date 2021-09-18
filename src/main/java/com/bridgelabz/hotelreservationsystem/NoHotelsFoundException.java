package com.bridgelabz.hotelreservationsystem;

import java.util.function.Supplier;

public class NoHotelsFoundException  extends RuntimeException {

    enum ExceptionType {
        HOTEL_LIST_EMPTY
    }
    ExceptionType type;

    public NoHotelsFoundException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
