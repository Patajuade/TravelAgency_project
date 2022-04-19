package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class PlaneStage extends TripStage {

    int flyingSpeed;
    int waitingTime;
    CityModel source;
    CityModel destination;
    double pricePerKm;

    int distance;

    public PlaneStage(FXMLLoader loader) {
        super(loader);
    }

    public int getDistance() {
        return distance;
    }

    public void distanceCompute() {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(Integer.parseInt(destination.getLatitude()) - Integer.parseInt(source.getLatitude()));
        double lonDistance = Math.toRadians(Integer.parseInt(destination.getLongitude()) - Integer.parseInt(source.getLongitude()));
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Integer.parseInt(source.getLatitude()))) *
                Math.cos(Math.toRadians(Integer.parseInt(destination.getLatitude())))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
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

    public void setDestination(CityModel destination){
        this.destination = destination;
    }

    public void setSource(CityModel source) {
        this.source = source;
    }
}
