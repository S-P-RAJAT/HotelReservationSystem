package com.bridgelabz.hotelreservationsystem;

import java.util.Map;


public class Hotel {

    private String hotelName;
    private int rating;

    private Map<CustomerType, Integer> weekDayRate;
    private Map<CustomerType, Integer> weekEndRate;


    public Hotel(String hotelName, int rating, Map<CustomerType, Integer> weekDayRate, Map<CustomerType, Integer> weekEndRate) {
        super();
        this.hotelName = hotelName;
        this.rating = rating;

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

    public Integer getWeekDayRate(CustomerType customerType) {
        return weekDayRate.get(customerType);
    }

    public void setWeekDayRate(Map<CustomerType, Integer> weekDayRate) {
        this.weekDayRate = weekDayRate;
    }

    public Integer getWeekEndRate(CustomerType customerType) {
        return weekEndRate.get(customerType);
    }

    public void setWeekEndRate(Map<CustomerType, Integer> weekEndRate) {
        this.weekEndRate = weekEndRate;
    }

}