package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;

public interface HotelReservation {
    void addHotel(String hotelName, int rating, int regularWeekDayRate, int regularWeekEndRate, int rewardWeekDayRate, int rewardWeekEndRate);

    int getSize();

    int calculateTotalCostForGivenHotel(Hotel hotel, String startDate, String endDate);

    ArrayList<Hotel> getCheapestHotelList(String startDate, String endDate);

    Hotel getCheapestBestRatedHotel(String startDate, String endDate);

    Hotel getBestRatedHotel(String startDate, String endDate);

    void setCustomerType(CustomerType customerType);
}
