package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class HotelReservation {
    Hotel hotel;
    ArrayList<Hotel> hotelList = new ArrayList<>();


    public void addHotel(String hotelName, int rating, CustomerType customerType, int weekDayRate, int weekEndRate) {

        hotel = new Hotel(hotelName, rating, customerType, weekDayRate, weekEndRate);
        hotelList.add(hotel);
    }

    public int getSize() {
        return hotelList.size();
    }
    public static long calculateWeekDays( LocalDate start,  LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
    public Hotel getCheapestHotel(LocalDate startDate, LocalDate endDate) {
        int noOfDaysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate);
        Hotel hotel = hotelList.stream()
                .min(Comparator.comparing(Hotel::getWeekDayRate))
                .orElse(null);
        if (hotel != null) {
            int cheapestPrice = hotel.getWeekDayRate();
            System.out.println("Hotel name: " + hotel.getHotelName() + " \nCheapest price: " + cheapestPrice * noOfDaysBetween);
        }
        return hotel;

    }
}
