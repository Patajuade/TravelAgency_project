package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;

public abstract class TripStage {
    protected double price;
    protected double duration;
    protected int numberOfNights;
    protected int pricePerNight;

    public FXMLLoader getFxml() {
        return fxmlLoader;
    }

    private FXMLLoader fxmlLoader;

    public TripStage(FXMLLoader loader) {
        this.fxmlLoader = loader;
    }

    public abstract void priceCompute();
    public abstract void durationCompute();

    public double getPrice() {
        return price;
    }
    public double getDuration() {
        return duration;
    }

    public int getNumberOfNights(){return 0;}
    public void setNumberOfNights(int numberOfNights) {}
    public void setPricePerNight(int pricePerNight) {}
}
