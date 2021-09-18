package com.bridgelabz.hotelreservationsystem;

import java.util.*;
import java.util.stream.Collectors;

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
    public void addHotel(String hotelName, int rating, int regularWeekDayRate, int regularWeekEndRate,
            int rewardWeekDayRate, int rewardWeekEndRate) {
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
        System.out.println(hotel.getHotelName());
        return hotel.getWeekDayRate(this.customer) * numberOfWeekDays
                + hotel.getWeekEndRate(this.customer) * (numberOfDaysBetween - numberOfWeekDays);
    }

    @Override
    public List<Hotel> getCheapestHotelList(String startDate, String endDate) {

        Integer cheapestPrice = hotelList.stream()
                .map(hotel1 -> calculateTotalCostForGivenHotel(hotel1, startDate, endDate))
                .min(Integer::compareTo)
                .orElse(null);

        return  hotelList.stream()
                .filter(hotel1 -> calculateTotalCostForGivenHotel(hotel1,startDate,endDate) == cheapestPrice)
                .collect(Collectors.toList());

    }

    @Override
    public Hotel getCheapestBestRatedHotel(String startDate, String endDate){
        List<Hotel> cheapestHotelList = getCheapestHotelList(startDate, endDate);
        return  cheapestHotelList.stream()
                .max(Comparator.comparing(Hotel::getRating))
                .orElse(null);
    }

    @Override
    public Hotel getBestRatedHotel(String startDate, String endDate){
        dateValidation(startDate, endDate);
        return  hotelList.stream()
                .max(Comparator.comparing(Hotel::getRating))
                .orElse(null);
    }

    @Override
    public void setCustomerType(CustomerType customerType){
        if(customerType != null) {
            this.customer = customerType;
        }
    }
}
