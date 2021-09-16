package com.bridgelabz.hotelreservationsystem;

enum CustomerType {
    REGULAR;
};

public class Hotel {

    private String hotelName;
    private int rating;

    CustomerType customerType;

    private int rate;

    public Hotel(String hotelName, int rating, CustomerType customerType, int rate) {
        super();
        this.hotelName = hotelName;
        this.rating = rating;
        this.customerType = customerType;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}