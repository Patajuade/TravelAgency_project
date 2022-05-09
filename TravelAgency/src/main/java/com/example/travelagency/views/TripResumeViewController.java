package com.example.travelagency.views;

import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.PlaneStage;
import com.example.travelagency.models.TripResume;
import com.example.travelagency.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class TripResumeViewController {

    @FXML
    private Label crossedCitiesLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label fromAndDateLabel;

    @FXML
    private Label nameTripLabel;

    @FXML
    private Label priceWaitingTimeDistanceLabel;

    @FXML
    private Button showButton;

    TripResume tripResume;

    public void setTripResume(TripResume tripResume) {
        this.tripResume = tripResume;
    }

    @FXML
    void deleteTripButtonClick(ActionEvent event) {
        listener.onClickDeleteTripButton();
    }

    @FXML
    void showTripButtonClick(ActionEvent event) throws IOException {
        listener.onClickShowTripButton();
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void UpdateLabelData(double totalDistance, double totalTime, double totalPrice) {
        priceWaitingTimeDistanceLabel.setText(totalDistance + " km " + totalTime + " h " + totalPrice + " euros");
    }

    public void UpdateLabelFromDate(CityModel source, String Date) {
        String toShow = "" ;
        if(source != null){
            toShow += "Depuis " + source.getCityName() + "(" + source.getCountryName() + "), ";
        }
        else if(Date != ""){
            toShow += Date;
        }
        fromAndDateLabel.setText(toShow);
    }

    public void UpdateCrossedCities(ArrayList<TripStage> stages){
        String toShow = "";
        if(tripResume.getSource() != null){
            toShow = "Villes traversées : " + tripResume.getSource().getCityName() + " (" + tripResume.getSource().getCountryName() + ")";
        }
        for(TripStage stage : stages){
            if(stage instanceof PlaneStage && stage.getDestination() != null){
                toShow += ", " + stage.getDestination().getCityName() + " (" + stage.getDestination().getCountryName() + ")";
            }
        }
        crossedCitiesLabel.setText(toShow);
    }

    public void UpdateNameTripLabel(String nameTrip){
        nameTripLabel.setText("Voyage " +  nameTrip);
    }

    public TripResume getTripResume() {
        return tripResume;
    }

    public interface Listener{
        void onClickDeleteTripButton();
        void onClickShowTripButton() throws IOException;
    }
}