package com.example.travelagency.models;

import java.util.ArrayList;

public class TripResume {
    ArrayList<TripStage> stages;


    CityModel Source;

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

}


