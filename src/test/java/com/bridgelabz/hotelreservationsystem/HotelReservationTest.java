package com.bridgelabz.hotelreservationsystem;


import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class HotelReservationTest {

    @Test
    public void add_LakeWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("LakeWood",3, CustomerType.REGULAR,110);
        Assert.assertEquals(1, hotelReservation.getSize());

    }
    @Test
    public void add_BrideWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("BridgeWood",4, CustomerType.REGULAR,160);
        Assert.assertEquals(1, hotelReservation.getSize());

    }
    @Test
    public void add_RidgeWoodHotel_WhenSuccessful_shouldChangeListSize() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("RidgeWood",5, CustomerType.REGULAR,220);
        Assert.assertEquals(1, hotelReservation.getSize());
    }
    @Test
    public void givenStartAndEndDate_WhenHotelListNotEmpty_ShouldReturnHotelWithCheapestPrice() {

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("LakeWood",3, CustomerType.REGULAR,110);
        hotelReservation.addHotel("BridgeWood",4, CustomerType.REGULAR,10);
        hotelReservation.addHotel("RidgeWood",5, CustomerType.REGULAR,220);

        LocalDateTime startDate = LocalDateTime.of(2021, 9, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 9, 12, 0, 0);
        Hotel hotel = hotelReservation.getCheapestHotel(startDate, endDate);
        Assert.assertEquals("BridgeWood", hotel.getHotelName());
    }
    @Test
    public void givenStartAndEndDate_WhenHotelListEmpty_ShouldReturnNull() {

        HotelReservation hotelReservation = new HotelReservation();
        LocalDateTime startDate = LocalDateTime.of(2021, 9, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 9, 12, 0, 0);
        Hotel hotel = hotelReservation.getCheapestHotel(startDate, endDate);
        Assert.assertNull(hotel);
    }
}