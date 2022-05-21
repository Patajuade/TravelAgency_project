package com.example.travelagency.views;

import be.helha.common.models.PlaneStage;
import be.helha.common.models.TripResume;
import be.helha.common.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

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
    void deleteTripButtonClick(ActionEvent event) throws IOException {
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

    public void UpdateLabelData() {
        priceWaitingTimeDistanceLabel.setText( String.format("%.2f",tripResume.getTotalDistance())+ " km " +  String.format("%.2f",tripResume.getTotalTime())+ " h " + String.format("%.2f",tripResume.getTotalPrice()) + " euros");
    }

    public void UpdateLabelFromDate(String Date) {
        String toShow = "" ;
        if(tripResume.getSource() != null){
            toShow += "Depuis " + tripResume.getSource().getCityName() + "(" + tripResume.getSource().getCountryName() + "), ";
        }
        if(Date != ""){
            toShow += Date;
        }
        fromAndDateLabel.setText(toShow);
    }

    public void UpdateCrossedCities(){
        String toShow = "";
        if(tripResume.getSource() != null){
            toShow = "Villes traversées : " + tripResume.getSource().getCityName() + " (" + tripResume.getSource().getCountryName() + ")";
        }
        for(TripStage stage : tripResume.getStages()){
            if(stage instanceof PlaneStage && stage.getDestination() != null){
                toShow += ", " + stage.getDestination().getCityName() + " (" + stage.getDestination().getCountryName() + ")";
            }
        }
        crossedCitiesLabel.setText(toShow);
    }

    public void UpdateNameTripLabel(String nameTrip){
        if(nameTrip !=null){
            nameTripLabel.setText(nameTrip);
        }
    }

    public TripResume getTripResume() {
        return tripResume;
    }

    public void UpdateDatas(String date,String name) {
        UpdateLabelData();
        UpdateLabelFromDate(date);
        UpdateNameTripLabel(name);
        UpdateCrossedCities();
    }

    public interface Listener{
        void onClickDeleteTripButton() throws IOException;
        void onClickShowTripButton() throws IOException;
    }
}