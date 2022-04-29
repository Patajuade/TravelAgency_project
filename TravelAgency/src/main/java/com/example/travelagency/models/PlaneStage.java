package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaneStage extends TripStage {

    int flyingSpeed;
    int waitingTime;

    double pricePerKm;

    double distance;

    public PlaneStage(FXMLLoader loader) {
        super(loader);
        flyingSpeed = 700;
        waitingTime = 0;
        pricePerKm = 0.025;
    }

    public double getDistance() {
        return distance;
    }

    public void distanceCompute() {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(destination.getLatitudeDouble() - source.getLatitudeDouble());
        double lonDistance = Math.toRadians(destination.getLongitudeDouble() - source.getLongitudeDouble());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(source.getLatitudeDouble())) *
                Math.cos(Math.toRadians(destination.getLatitudeDouble()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceLocal = R * c * 1000; // convert to meters
        distanceLocal = Math.pow(distanceLocal, 2);
        distance = Math.sqrt(distanceLocal)/1000;
        //Pour n'afficher que 2 décimales sans prise de tête
        distance = (double) Math.round(distance * 100) / 100;
    }

    @Override
    public void priceCompute() {
        price = distance * pricePerKm;
        price = (double) Math.round(price * 100) / 100;
    }

    @Override
    public void durationCompute() {
        duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
        duration = (double) Math.round(duration * 100) / 100;
    }

    public void setFlyingSpeed(int flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }


}
