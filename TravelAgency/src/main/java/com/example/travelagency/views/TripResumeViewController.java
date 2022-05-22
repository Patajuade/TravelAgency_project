package com.example.travelagency.views;

import com.example.travelagency.models.PlaneStage;
import com.example.travelagency.models.TripResume;
import com.example.travelagency.models.TripStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * View controller for TripResume anchor pane
 */
public class TripResumeViewController {

    @FXML
    private Label crossedCitiesLabel;

    @FXML
    private Label fromAndDateLabel;

    @FXML
    private Label nameTripLabel;

    @FXML
    private Label priceWaitingTimeDistanceLabel;

    TripResume tripResume;

    private Listener listener;

    /**
     * Listener interface
     */
    public interface Listener{
        void onClickDeleteTripButton() throws IOException;
        void onClickShowTripButton() throws IOException;
    }

    /**
     * Listener of the deleteButton
     * this function is used by Scene Builder in the OnAction button event
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    void deleteTripButtonClick() throws IOException {
        listener.onClickDeleteTripButton();
    }

    /**
     * Listener of the showButton
     * this function is used by Scene Builder in the OnAction button event
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    void showTripButtonClick() throws IOException {
        listener.onClickShowTripButton();
    }

    /**
     * Updates the text of the total distance, total price and total time labels
     */
    public void UpdateLabelData() {
        priceWaitingTimeDistanceLabel.setText(tripResume.getTotalDistance() + " km " + tripResume.getTotalTime() + " h " + tripResume.getTotalPrice() + " euros");
    }

    /**
     * Updates fromAndDateLabel text
     * @param Date is the date of the trip
     */
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

    /**
     * Updates crossed cities text
     */
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

    /**
     * Updates the text of the TripName label
     * @param nameTrip is the name of the trip
     */
    public void UpdateNameTripLabel(String nameTrip){
        if(nameTrip !=null){
            nameTripLabel.setText(nameTrip);
        }
    }

    /**
     * Updates Data, FromDate, TripLabel and crossed cities labels.
     * @param date
     * @param name
     */
    public void UpdateDatas(String date,String name) {
        UpdateLabelData();
        UpdateLabelFromDate(date);
        UpdateNameTripLabel(name);
        UpdateCrossedCities();
    }

    public TripResume getTripResume() {
        return tripResume;
    }

    public void setTripResume(TripResume tripResume) {
        this.tripResume = tripResume;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}