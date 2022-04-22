package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

public abstract class TripStage {
    protected double price;
    protected double duration;
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
        priceCompute();
        return price;
    }

    public double getDuration() {
        durationCompute();
        return duration;
    }
}
