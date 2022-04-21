package com.example.travelagency.models;

import com.example.travelagency.controllers.TravelAgencyApplication;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class HotelStage extends TripStage {

    int numberOfNights;
    int pricePerNight;

    public HotelStage() {
        super(new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml")));
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
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
