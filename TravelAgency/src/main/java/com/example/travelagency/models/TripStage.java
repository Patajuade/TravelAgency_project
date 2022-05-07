package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class TripStage {
    protected double price;
    protected double duration;
    protected double distance;

    public CityModel getSource() {
        return source;
    }

    public CityModel getDestination() {
        return destination;
    }

    protected CityModel source;
    protected CityModel destination;
    public FXMLLoader getFxml() {
        return fxmlLoader;
    }

    private FXMLLoader fxmlLoader;

    private AnchorPane anchorPane;

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

    public void setDestination(CityModel destination){
        this.destination = destination;
    }

    public void setSource(CityModel source) {
        this.source = source;
    }

    public void setAnchorPane() throws IOException {
        anchorPane = fxmlLoader.load();
    }

    public AnchorPane getAnchorPane() throws IOException {
        return anchorPane;
    }
}
