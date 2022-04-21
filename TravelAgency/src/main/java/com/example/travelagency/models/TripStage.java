package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

public abstract class TripStage {
    protected double price;
    protected double duration;
    ArrayList<TripStage> stages= new ArrayList<>();
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
}
