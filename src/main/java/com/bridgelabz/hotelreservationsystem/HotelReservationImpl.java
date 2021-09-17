package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.bridgelabz.hotelreservationsystem.DateServiceProvider.*;

public class HotelReservationImpl implements HotelReservation {

    Hotel hotel;
    ArrayList<Hotel> hotelList;
    CustomerType customer;

    public HotelReservationImpl() {

        this.hotelList = new ArrayList<>();
        this.customer = CustomerType.REGULAR;
    }

    @Override
    public void addHotel(String hotelName, int rating, int regularWeekDayRate, int regularWeekEndRate, int rewardWeekDayRate, int rewardWeekEndRate) {

        Map<CustomerType, Integer> weekDayRateMap = new HashMap<>();
        Map<CustomerType, Integer> weekEndRateMap = new HashMap<>();

        weekDayRateMap.put(CustomerType.REGULAR, regularWeekDayRate);
        weekEndRateMap.put(CustomerType.REGULAR, regularWeekEndRate);

        weekDayRateMap.put(CustomerType.REWARD, rewardWeekDayRate);
        weekEndRateMap.put(CustomerType.REWARD, rewardWeekEndRate);

        hotel = new Hotel(hotelName, rating, weekDayRateMap, weekEndRateMap);
        hotelList.add(hotel);
    }

    @Override
    public int getSize() {

        return hotelList.size();
    }


    @Override
    public int calculateTotalCostForGivenHotel(Hotel hotel, String startDate, String endDate) {

        dateValidation(startDate, endDate);
        int numberOfDaysBetween = calculateTotalDays(startDate, endDate);
        int numberOfWeekDays = calculateWeekDays(startDate, endDate);
        return hotel.getWeekDayRate(this.customer) * numberOfWeekDays + hotel.getWeekEndRate(this.customer) * (numberOfDaysBetween - numberOfWeekDays);
    }

    @Override
    public ArrayList<Hotel> getCheapestHotelList(String startDate, String endDate) {

        Hotel hotel = hotelList.stream()
                .min(Comparator.comparing(hotel1 -> calculateTotalCostForGivenHotel(hotel1, startDate, endDate)))
                .orElse(null);
        ArrayList<Hotel> hotelList2 = new ArrayList<>();

        if (hotel != null) {

            int cheapestPrice = calculateTotalCostForGivenHotel(hotel, startDate, endDate);
            for (Hotel newHotel : hotelList) {

                int hotelPrice = calculateTotalCostForGivenHotel(newHotel, startDate, endDate);
                if (hotelPrice == cheapestPrice) {

                    hotelList2.add(newHotel);
                    System.out.println("Hotel name: " + newHotel.getHotelName() + " \nCheapest price: " + cheapestPrice);
                }
            }
        }
        return hotelList2;

    }
    @Override
    public Hotel getCheapestBestRatedHotel(String startDate, String endDate){
        ArrayList<Hotel> cheapestHotelList = getCheapestHotelList(startDate, endDate);
        int rate = 0;
        Hotel bestHotel = null;
        for (Hotel hotel: cheapestHotelList) {
            if(rate<hotel.getRating()){
                rate = hotel.getRating();
                bestHotel = hotel;
            }
        }
        return bestHotel;
    }
    @Override
    public Hotel getBestRatedHotel(String startDate, String endDate){
        dateValidation(startDate, endDate);
        int rate = 0;
        Hotel bestHotel = null;
        for (Hotel hotel: hotelList) {
            if(rate<hotel.getRating()){
                rate = hotel.getRating();
                bestHotel = hotel;
            }
        }
        return bestHotel;
    }
    @Override
    public void setCustomerType(CustomerType customerType){
        if(customerType != null) {
            this.customer = customerType;
        }
    }
}
