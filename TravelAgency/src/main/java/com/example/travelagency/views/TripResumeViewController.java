package com.example.travelagency.views;

import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.TripResume;
import com.example.travelagency.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        fromAndDateLabel.setText("Depuis " + source.getCityName() + "(" + source.getCountryName() + "), " + Date);
    }

    public void UpdateCrossedCities(ArrayList<TripStage> stages){
        String toShow = "Villes traversées : " + tripResume.getSource().getCityName() + " (" + tripResume.getSource().getCountryName() + ")";
        for(TripStage stage : stages){
            toShow += ", " + stage.getDestination().getCityName() + " (" + stage.getDestination().getCountryName() + ")";
        }
        crossedCitiesLabel.setText(toShow);
    }

    public void UpdateNameTripLabel(String nameTrip){
        nameTripLabel.setText("Voyage " +  nameTrip);
    }

    public interface Listener{
        void onClickDeleteTripButton();
        void onClickShowTripButton() throws IOException;
    }
}