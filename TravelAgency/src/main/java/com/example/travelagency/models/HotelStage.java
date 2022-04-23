package com.example.travelagency.models;

import com.example.travelagency.controllers.TravelAgencyApplication;
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
    @Override
    public int getNumberOfNights() {
        return numberOfNights;
    }
    @Override
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
    @Override
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
