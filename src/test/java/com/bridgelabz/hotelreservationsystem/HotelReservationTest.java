package com.bridgelabz.hotelreservationsystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelReservationTest {

    HotelReservation hotelReservation;
    LocalDate startDate;
    LocalDate endDate;
    @Before
    public void initialize(){

        hotelReservation = new HotelReservation();
        hotelReservation.addHotel("LakeWood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("BridgeWood", 4, 150, 50, 110, 50);
        hotelReservation.addHotel("RidgeWood", 5, 220, 150, 100, 40);

        startDate = LocalDate.of(2020, 9, 11);
        endDate = LocalDate.of(2020, 9, 12);

    }
    @Test
    public void addingHotels_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
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
        Assert.assertEquals(200, hotelReservation.calculateTotalCostForGivenHotel(hotel[0], startDate, endDate));

    }

    @Test
    public void givenStartAndEndDate_WhenHotelListEmpty_ShouldReturnNull() {

        HotelReservation hotelReservation = new HotelReservation();
        ArrayList<Hotel> hotel = hotelReservation.getCheapestHotelList(startDate, endDate);
        Assert.assertTrue(hotel.isEmpty());
    }
    @Test
    public void givenStartAndEndDate_MethodGetCheapestBestRatedHotel_ShouldReturnCheapestHotelWithHighestRating() {
        
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("BridgeWood", hotel.getHotelName());
    }
    @Test
    public void givenStartAndEndDate_MethodGetBestRatedHotel_ShouldReturnHotelWithHighestRating() {
        
        Hotel hotel = hotelReservation.getBestRatedHotel(startDate, endDate);
        Assert.assertEquals("RidgeWood", hotel.getHotelName());
    }
    @Test
    public void givenStartAndEndDate_MethodGetCheapestBestRatedHotel_ShouldReturnCheapestHotelWithHighestRatingForRewardCustomers() {

        hotelReservation.setCustomerType(CustomerType.REWARD);
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("RidgeWood", hotel.getHotelName());
        Assert.assertEquals(5, hotel.getRating());
        Assert.assertEquals(140, hotelReservation.calculateTotalCostForGivenHotel(hotel, startDate, endDate));

    }
    @Test
    public void givenStartAndEndDate_WhenCustomerTypeIsNull_ShouldReturnCheapestHotelWithHighestRatingForRegularCustomers() {

        hotelReservation.setCustomerType(null);
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
        Assert.assertEquals("BridgeWood", hotel.getHotelName());

    }
}