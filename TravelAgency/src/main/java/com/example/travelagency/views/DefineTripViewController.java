package com.example.travelagency.views;

import com.example.travelagency.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * View controller for DefineTrip window
 */
public class DefineTripViewController {

    @FXML
    private TextField TripNameTextField;
    @FXML
    private Button ChooseButton;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private Button AddPlaneStageButton;
    @FXML
    private Button AddHotelStageButton;
    @FXML
    private Label dataLabel;
    @FXML
    private VBox StageVbox;

    private Listener listener;

    TripResume tripResume;

    /**
     * Listener interface
     */
    public interface Listener {
        void onClickChooseDestinationButton() throws IOException;
        void onClickAddPlaneButton() throws IOException;
        void onClickAddHotelButton() throws IOException;
    }

    /**
     * listener of the ChooseButton
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    private void handleChooseButtonClick() throws IOException {
        listener.onClickChooseDestinationButton();
    }

    /**
     * listener of the AddPlaneStageButton
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    private void handleAddPlaneStageButtonClick() throws IOException {
        listener.onClickAddPlaneButton();
    }

    /**
     * listener of the AddHotelStageButton
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    private void handleAddHotelStageButtonClick() throws IOException {
        listener.onClickAddHotelButton();
    }

    /**
     * Changes the text on the choose button when a city is selected
     * @param city name of the selected city
     */
    public void changeStartCity(CityModel city){
        if (city != null) {
            ChooseButton.setText(city.getCityName()+ "(" + city.getCountryName() + ")");
        }
    }

    /**
     * Adds an anchor pane to the main stage Vbox
     * StageVbox is the vbox in which the HotelStage and PlaneStage anchor panes are going
     * @param anchorPane is the anchor pane added
     */
    public void addStageToStageVBOX(AnchorPane anchorPane){
        StageVbox.getChildren().add(anchorPane);
    }

    /**
     * Removes anchor pane from the main stage Vbox
     * StageVbox is the vbox in which the HotelStage and PlaneStage anchor panes are going
     * @param anchorPane is the anchor pane added
     * @throws IOException management of input/output exceptions.
     */
    public void deleteStageOfStageVBOX(AnchorPane anchorPane) throws IOException {
        StageVbox.getChildren().remove(anchorPane);
    }

    /**
     * Updates the total labels when a stage is added or removed
     * @param totalDistance is the total km of the trip
     * @param totalTime is the total time the trip will last
     * @param totalPrice is the total cost of the trip
     */
    public void updateLabel(double totalDistance, double totalTime, double totalPrice){
        dataLabel.setText(totalDistance + " km " + totalTime + " h " + totalPrice + " euros");
    }

    /**
     * Updates the name, date and starting city of the trip
     */
    public void updateDatas(){
        setName(tripResume.getName());
        setDate(tripResume.getDate());
        changeStartCity(tripResume.getSource());
    }

    public LocalDate getDate() {
        return DatePicker.getValue();
    }

    public String getNameTrip(){
        return TripNameTextField.getText();
    }

    public String getDateAsString(){
        String chosenDateAsString = "";
        if(DatePicker.getValue() != null){
            LocalDate chosenDate = DatePicker.getValue();
            chosenDateAsString = chosenDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return chosenDateAsString;
    }

    public void setTripResume(TripResume tripResume) {
        this.tripResume = tripResume;
    }

    public void setName(String name) {
        TripNameTextField.setText(name);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void setDate(LocalDate date) {
        DatePicker.setValue(date);
    }
}
