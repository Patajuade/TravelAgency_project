package com.example.travelagency.views;

import com.example.travelagency.models.*;
import com.example.travelagency.controllers.TravelAgencyApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DefineTripController {

    @FXML
    private TextField TripNameTextField;
    @FXML
    private Button ChooseButton;
    @FXML
    private DatePicker DateField;
    @FXML
    private Button AddPlaneStageButton;
    @FXML
    private Button AddHotelStageButton;
    @FXML
    private Label KmLabel;
    @FXML
    private Label HoursLabel;
    @FXML
    private Label EurosLabel;
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

    public void addStageToStageVBOX(TripStage tripStage) throws IOException {
        StageVbox.getChildren().add(tripStage.getFxml().load());
    }

    public void deleteStageOfStageVBOX(TripStage tripStage) {
        StageVbox.getChildren().remove(tripStage.getFxml());
    }

}
