package be.helha.travelagency.views;

import be.helha.common.models.PlaneStage;
import be.helha.common.models.TripResume;
import be.helha.common.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
     * Contains action functions
     */
    public interface Listener{
        /**
         * Action when DeleteTripButton is clicked
         * @throws IOException management of input/output exceptions.
         */
        void onClickDeleteTripButton() throws IOException;

        /**
         * Action when ShowTripButton is clicked
         * @throws IOException management of input/output exceptions.
         */
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
        priceWaitingTimeDistanceLabel.setText( String.format("%.2f",tripResume.getTotalDistance())+ " km " +  String.format("%.2f",tripResume.getTotalTime())+ " h " + String.format("%.2f",tripResume.getTotalPrice()) + " euros");
    }

    /**
     * Updates fromAndDateLabel text
     */
    public void UpdateLabelFromDate() {
        String toShow = "" ;
        if(tripResume.getSource() != null){
            toShow += "Depuis " + tripResume.getSource().getCityName() + "(" + tripResume.getSource().getCountryName() + "), ";
        }
        if(tripResume.getDate() != null){
            toShow += tripResume.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
     */
    public void UpdateNameTripLabel(){
        if(tripResume.getName() !=null){
            nameTripLabel.setText(tripResume.getName());
        }
    }

    /**
     * Updates Data, FromDate, TripLabel and crossed cities labels.
     */
    public void UpdateDatas() {
        UpdateLabelData();
        UpdateLabelFromDate();
        UpdateNameTripLabel();
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