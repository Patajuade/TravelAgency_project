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
        void onClickAddPlaneButton(TripStage stage);
        void onClickAddHotelButton(TripStage stage);
    }

    ChooseDestinationViewController chooseDestinationViewController;
    //TODO: Fonction qui appelle la fenêtre ChooseDestination quand on clique sur le bouton choisir
    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        listener.onClickChooseDestinationButton();
    }

    public void changeStartCity(CityModel city){
        ChooseButton.setText(city.getCityName()+ "(" + city.getCountryName() + ")");
    }

    @FXML
    private void handleAddPlaneStageButtonClick(ActionEvent event) throws IOException {
        TripStage stage = new PlaneStage(new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml")));
        StageVbox.getChildren().add(stage.getFxml().load());
        listener.onClickAddPlaneButton(stage);
    }

    @FXML
    private void handleAddHotelStageButtonClick(ActionEvent event) throws IOException {
        TripStage stage = new HotelStage(new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml")));
        StageVbox.getChildren().add(stage.getFxml().load());
        listener.onClickAddHotelButton(stage);
    }

    public void changeChooseButtonText(String string){

    }

}
