package com.bridgelabz.hotelreservationsystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.InvalidDateException.*;

import java.util.ArrayList;
import java.util.List;

public class HotelReservationTest {

    HotelReservation hotelReservation;
    String startDate;
    String endDate;

    @Before
    public void initialize() {
        hotelReservation = new HotelReservationImpl();
        hotelReservation.addHotel("LakeWood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("BridgeWood", 4, 150, 50, 110, 50);
        hotelReservation.addHotel("RidgeWood", 5, 220, 150, 100, 40);

        startDate = "11Sep2020";
        endDate = "12Sep2020";
    }

    @Test
    public void addingHotels_WhenSuccessful_shouldChangeListSize() {
        HotelReservation hotelReservation = new HotelReservationImpl();

        hotelReservation.addHotel("LakeWood", 3, 110, 90, 80, 80);
        Assert.assertEquals(1, hotelReservation.getSize());
        hotelReservation.addHotel("BridgeWood", 4, 150, 50, 110, 50);
        Assert.assertEquals(2, hotelReservation.getSize());
        hotelReservation.addHotel("RidgeWood", 5, 220, 150, 100, 40);
        Assert.assertEquals(3, hotelReservation.getSize());
    }

    @Test
    public void givenStartAndEndDate_WhenHotelListNotEmpty_ShouldReturnHotelsWithMostCheapestPrice() {
        Hotel[] hotel = hotelReservation.getCheapestHotelList(startDate, endDate).toArray(new Hotel[0]);
        Assert.assertEquals("LakeWood", hotel[0].getHotelName());
        Assert.assertEquals("BridgeWood", hotel[1].getHotelName());
        Assert.assertEquals(200,
                hotelReservation.calculateTotalCostForGivenHotel(hotel[0], startDate, endDate));
    }

    @Test
    public void givenStartAndEndDate_WhenHotelListEmpty_ShouldReturnNull() {
        HotelReservation hotelReservation = new HotelReservationImpl();
        try {
            List<Hotel> hotel = hotelReservation.getCheapestHotelList(startDate, endDate);
        } catch (NoHotelsFoundException e){
            Assert.assertEquals(NoHotelsFoundException.ExceptionType.HOTEL_LIST_EMPTY,e.type);
        }
    }

    @Test
    public void givenStartAndEndDate_WhenValid_ShouldReturnCheapestHotelWithHighestRating() {
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("BridgeWood", hotel.getHotelName());
    }

    @Test
    public void givenStartAndEndDate_WhenValid_ShouldReturnHotelWithHighestRating() {
        Hotel hotel = hotelReservation.getBestRatedHotel(startDate, endDate);
        Assert.assertEquals("RidgeWood", hotel.getHotelName());
    }

    @Test
    public void givenDateRange_WhenValid_ShouldReturnCheapestHotelWithHighestRatingForRewardCustomers() {
        hotelReservation.setCustomerType(CustomerType.REWARD);
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("RidgeWood", hotel.getHotelName());
        Assert.assertEquals(5, hotel.getRating());
        Assert.assertEquals(140,
                hotelReservation.calculateTotalCostForGivenHotel(hotel, startDate, endDate));
    }

    @Test
    public void
    givenCustomerType_WhenIsNull_ShouldReturnCheapestHotelWithHighestRatingForRegularCustomers() {
        hotelReservation.setCustomerType(null);
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("BridgeWood", hotel.getHotelName());
    }

    @Test
    public void givenDateRange_WhenInvalid_ShouldThrowException() {
        try {
            String initialDate = "123-123";
            String finalDate = "10Jan2020";
            hotelReservation.getCheapestHotelList(initialDate, finalDate);
        } catch (InvalidDateException e) {
            Assert.assertEquals(ExceptionType.INVALID_DATE_FORMAT, e.type);
        }
    }

    @Test
    public void givenDateRange_WhenFinalDateLesserThanInitialDate_ShouldThrowException() {
        try {
            String initialDate = "10Aug2020";
            String finalDate = "09Aug2020";
            hotelReservation.getCheapestHotelList(initialDate, finalDate);
        } catch (InvalidDateException e) {
            Assert.assertEquals(ExceptionType.INVALID_DATES_ORDER, e.type);
        }
    }

    @Test
    public void givenDateRange_WhenEnteredNullDates_ShouldThrowException() {
        try {
            hotelReservation.getCheapestHotelList(null, null);
        } catch (InvalidDateException e) {
            Assert.assertEquals(ExceptionType.ENTERED_NULL, e.type);
        }
    }

    @Test
    public void givenDateRange_WhenEnteredEmptyDates_ShouldThrowException() {
        try {
            hotelReservation.getCheapestHotelList("", "");
        } catch (InvalidDateException e) {
            Assert.assertEquals(ExceptionType.ENTERED_EMPTY, e.type);
        }
    }
}