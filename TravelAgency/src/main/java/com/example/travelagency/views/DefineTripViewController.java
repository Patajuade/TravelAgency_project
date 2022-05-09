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

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private Listener listener;

    public interface Listener {
        void onClickChooseDestinationButton() throws IOException;
        void onClickAddPlaneButton() throws IOException;
        void onClickAddHotelButton() throws IOException;
    }

    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        listener.onClickChooseDestinationButton();
    }

    public void changeStartCity(CityModel city){
        ChooseButton.setText(city.getCityName()+ "(" + city.getCountryName() + ")");
    }

    @FXML
    private void handleAddPlaneStageButtonClick(ActionEvent event) throws IOException {
        listener.onClickAddPlaneButton();
    }

    @FXML
    private void handleAddHotelStageButtonClick(ActionEvent event) throws IOException {
        listener.onClickAddHotelButton();
    }

    public void addStageToStageVBOX(AnchorPane anchorPane) throws IOException {
        StageVbox.getChildren().add(anchorPane);
    }

    public void deleteStageOfStageVBOX(AnchorPane anchorPane) throws IOException {
        StageVbox.getChildren().remove(anchorPane);
    }

    public void UpdateLabel(double totalDistance, double totalTime,double totalPrice){
        dataLabel.setText(totalDistance + " km " + totalTime + " h " + totalPrice + " euros");
    }

    public String getNameTrip(){
        return TripNameTextField.getText();
    }

    public String getDate(){
        String chosenDateAsString = "";
        if(DatePicker.getValue() != null){
            LocalDate chosenDate = DatePicker.getValue();
            chosenDateAsString = chosenDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return chosenDateAsString;
    }
}
