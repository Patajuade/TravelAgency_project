package com.example.travelagency.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class TripResume {
    ArrayList<TripStage> stages;
    private FXMLLoader fxmlLoader;
    public FXMLLoader getFxml() {
        return fxmlLoader;
    }

    CityModel Source;

    public TripResume(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
        this.stages = new ArrayList<>();
    }

    private AnchorPane anchorPane;
    public void setAnchorPane() throws IOException {
        anchorPane = fxmlLoader.load();
    }

    public AnchorPane getAnchorPane() throws IOException {
        return anchorPane;
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


