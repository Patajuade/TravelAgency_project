package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public abstract class TripStage {
    protected double price;
    protected double duration;

    public FXMLLoader getLoader() {
        return loader;
    }

    private FXMLLoader loader;

    public TripStage(FXMLLoader loader) {
        this.loader = loader;
    }


    public abstract void priceCompute();
    public abstract void durationCompute();

    public double getPrice() {
        return price;
    }

    public double getDuration() {
        return duration;
    }
}
