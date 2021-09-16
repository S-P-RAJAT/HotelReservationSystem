package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;

public class HotelReservation {
    Hotel hotel;
    ArrayList<Hotel> hotels = new ArrayList<>();

    public void addHotels(String hotelName, int rating, CustomerType customerType, int rate) {

        hotel = new Hotel(hotelName, rating, customerType, rate);
        hotels.add(hotel);
    }
    public int getSize(){
        return hotels.size();
    }
}
