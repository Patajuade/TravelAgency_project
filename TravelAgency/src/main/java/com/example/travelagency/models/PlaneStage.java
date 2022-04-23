package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class PlaneStage extends TripStage {

    int flyingSpeed;
    int waitingTime;

    double pricePerKm;

    double distance;

    public PlaneStage(FXMLLoader loader) {
        super(loader);
    }

    public double getDistance() {
        return distance;
    }

    public void distanceCompute() {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(Double.parseDouble(destination.getLatitude()) - Double.parseDouble(source.getLatitude()));
        double lonDistance = Math.toRadians(Double.parseDouble(destination.getLongitude()) - Double.parseDouble(source.getLongitude()));
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(source.getLatitude()))) *
                Math.cos(Math.toRadians(Double.parseDouble(destination.getLatitude())))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceLocal = R * c * 1000; // convert to meters
        distanceLocal = Math.pow(distance, 2);
        distance = Math.sqrt((distanceLocal));

    }


    @Override
    public void priceCompute() {
        price = distance * pricePerKm;
    }

    @Override
    public void durationCompute() {
        duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
    }

    public void setFlyingSpeed(int flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPricePerKm(int pricePerKm) {
        this.pricePerKm = pricePerKm;
    }


}
