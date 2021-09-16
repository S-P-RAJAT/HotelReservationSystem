package com.bridgelabz.hotelreservationsystem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class HotelReservation {
    Hotel hotel;
    ArrayList<Hotel> hotelList = new ArrayList<>();


    public void addHotel(String hotelName, int rating, CustomerType customerType, int rate) {

        hotel = new Hotel(hotelName, rating, customerType, rate);
        hotelList.add(hotel);
    }
    public int getSize(){
        return hotelList.size();
    }

    public Hotel getCheapestHotel(LocalDateTime startDate, LocalDateTime endDate) {
        int noOfDaysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate);
        Hotel hotel = hotelList.stream()
                .min(Comparator.comparing(Hotel::getRate))
                .orElse(null);

        int cheapestPrice=hotel.getRate();
        System.out.println("Hotel name: "+hotel.getHotelName()+" \nCheapest price: "+cheapestPrice*noOfDaysBetween);
        return hotel;

    }
}
