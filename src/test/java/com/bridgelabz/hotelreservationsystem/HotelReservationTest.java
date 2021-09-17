package com.bridgelabz.hotelreservationsystem;


import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelReservationTest {

    @Test
    public void add_LakeWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("LakeWood", 3, 110, 90);
        Assert.assertEquals(1, hotelReservation.getSize());

    }

    @Test
    public void add_BrideWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("BridgeWood", 4, 150, 50);
        Assert.assertEquals(1, hotelReservation.getSize());

    }

    @Test
    public void add_RidgeWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("RidgeWood", 5, 220, 150);
        Assert.assertEquals(1, hotelReservation.getSize());
    }

    @Test
    public void givenStartAndEndDate_WhenHotelListNotEmpty_ShouldReturnHotelsWithMostCheapestPrice() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("LakeWood", 3, 110, 90);
        hotelReservation.addHotel("BridgeWood", 4, 150, 50);
        hotelReservation.addHotel("RidgeWood", 5, 220, 150);

        LocalDate startDate = LocalDate.of(2020, 9, 11);
        LocalDate endDate = LocalDate.of(2020, 9, 12);
        Hotel[] hotel = hotelReservation.getCheapestHotelList(startDate, endDate).toArray(new Hotel[0]);
        Assert.assertEquals("LakeWood", hotel[0].getHotelName());
        Assert.assertEquals("BridgeWood", hotel[1].getHotelName());
        Assert.assertEquals(200, hotelReservation.calculateTotalCostForGivenHotel(hotel[0], startDate, endDate));

    }

    @Test
    public void givenStartAndEndDate_WhenHotelListEmpty_ShouldReturnNull() {

        HotelReservation hotelReservation = new HotelReservation();
        LocalDate startDate = LocalDate.of(2020, 9, 11);
        LocalDate endDate = LocalDate.of(2020, 9, 12);
        ArrayList<Hotel> hotel = hotelReservation.getCheapestHotelList(startDate, endDate);
        Assert.assertTrue(hotel.isEmpty());
    }
}