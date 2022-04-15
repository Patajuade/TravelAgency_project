package com.example.travelagency.models;

public class HotelStage extends TripStage {

    int numberOfNights;
    int pricePerNight;
    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }

    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }
}
