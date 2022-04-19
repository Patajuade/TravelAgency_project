package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class HotelStage extends TripStage {

    int numberOfNights;
    int pricePerNight;

    public HotelStage(FXMLLoader loader) {
        super(loader);
    }


    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }

    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }
}
