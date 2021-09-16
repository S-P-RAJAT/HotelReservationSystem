package com.bridgelabz.hotelreservationsystem;


import org.junit.Assert;
import org.junit.Test;

public class HotelReservationTest {

    @Test
    public void add_LakeWoodHotel_shouldReturnSize() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotels("LakeWood",3, CustomerType.REGULAR,110);
        Assert.assertEquals(1, hotelReservation.getSize());

    }
    @Test
    public void add_BrideWoodHotel_shouldReturnSize() {
        HotelReservation hotelReservation = new HotelReservation();

        hotelReservation.addHotels("BridgeWood",4, CustomerType.REGULAR,160);
        Assert.assertEquals(1, hotelReservation.getSize());



    }
    @Test
    public void add_RidgeWoodHotel_shouldReturnSize() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotels("RidgeWood",5, CustomerType.REGULAR,220);
        Assert.assertEquals(1, hotelReservation.getSize());


    }
}