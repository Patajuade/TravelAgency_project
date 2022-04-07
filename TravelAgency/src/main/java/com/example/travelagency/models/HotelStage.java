package com.example.travelagency.models;

public class HotelStage extends Stage{

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
