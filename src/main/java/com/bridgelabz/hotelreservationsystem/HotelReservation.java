package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HotelReservation {

    Hotel hotel;
    ArrayList<Hotel> hotelList;
    CustomerType customer;

    public HotelReservation() {

        this.hotelList = new ArrayList<>();
        this.customer = CustomerType.REGULAR;
    }

    public void addHotel(String hotelName, int rating, int weekDayRate, int weekEndRate) {

        Map<CustomerType, Integer> weekDayRateMap = new HashMap<>();
        weekDayRateMap.put(CustomerType.REGULAR, weekDayRate);
        Map<CustomerType, Integer> weekEndRateMap = new HashMap<>();
        weekEndRateMap.put(CustomerType.REGULAR, weekEndRate);

        hotel = new Hotel(hotelName, rating, weekDayRateMap, weekEndRateMap);
        hotelList.add(hotel);
    }

    public int getSize() {

        return hotelList.size();
    }

    public static long calculateWeekDays(LocalDate start, LocalDate end) {

        final DayOfWeek startDay = start.getDayOfWeek();
        final DayOfWeek endDay = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startDay == DayOfWeek.SUNDAY ? 1 : 0) + (endDay == DayOfWeek.SUNDAY ? 1 : 0);
    }

    public int calculateTotalCostForGivenHotel(Hotel hotel, LocalDate startDate, LocalDate endDate) {

        int numberOfDaysBetween =  endDate.plusDays(1).compareTo(startDate);
        int numberOfWeekDays = (int) calculateWeekDays(startDate, endDate.plusDays(1));
        return hotel.getWeekDayRate(this.customer) * numberOfWeekDays + hotel.getWeekEndRate(this.customer) * (numberOfDaysBetween - numberOfWeekDays);
    }

    public ArrayList<Hotel> getCheapestHotelList(LocalDate startDate, LocalDate endDate) {

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
    public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate){
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
    public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate){
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
}
