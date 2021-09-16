package com.bridgelabz.hotelreservationsystem;

enum CustomerType {
    REGULAR
}

public class Hotel {

    private String hotelName;
    private int rating;
    private CustomerType customerType;
    private int weekDayRate;
    private int weekEndRate;

    public Hotel(String hotelName, int rating, CustomerType customerType, int weekDayRate, int weekEndRate) {
        super();
        this.hotelName = hotelName;
        this.rating = rating;
        this.customerType = customerType;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public int getWeekDayRate() {
        return weekDayRate;
    }

    public void setWeekDayRate(int weekDayRate) {
        this.weekDayRate = weekDayRate;
    }
}