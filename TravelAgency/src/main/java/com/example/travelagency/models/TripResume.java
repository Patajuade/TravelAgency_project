package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TripResume {

    String name;
    LocalDate date;
    ArrayList<TripStage> stages;
    CityModel Source;

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    double totalDistance;
    double totalTime;
    double totalPrice;

    public void calculateAll(){
        totalTime = 0;
        totalDistance = 0;
        totalPrice = 0;
        for(TripStage tripStage :stages){
            if(tripStage.getDestination() != null && tripStage.getSource() != null){
                totalDistance += tripStage.getDestination().distanceCompute(tripStage.getSource());
            }
            totalTime += tripStage.getDuration();
            totalPrice += tripStage.getPrice();
        }
    }


    public TripResume() {
        this.stages = new ArrayList<>();
    }


    public void addStage(TripStage tripStage){
        stages.add(tripStage);
    }

    public void removeStage(TripStage tripStage){
        stages.remove(tripStage);
    }

    public void setSource(CityModel city){
        Source = city;
    }

    public CityModel getSource() {
        return Source;
    }

    public ArrayList<TripStage> getStages() {
        return stages;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String nameTrip) {
        this.name = nameTrip;
    }
}


