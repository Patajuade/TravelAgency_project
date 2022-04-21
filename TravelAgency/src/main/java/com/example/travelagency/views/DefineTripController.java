package com.example.travelagency.views;

import com.example.travelagency.interfaces.DefineTripListener;
import com.example.travelagency.models.*;
import com.example.travelagency.controllers.TravelAgencyApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public void setDefineTripListenner(DefineTripListener defineTripListenner) {
        this.defineTripListenner = defineTripListenner;
    }

    private DefineTripListener defineTripListenner;

    ArrayList<TripStage> stages= new ArrayList<>();

    ChooseDestinationViewController chooseDestinationViewController;
    CityModel cityModel;

    public String getTripNameTextFieldText(){
        return TripNameTextField.getText();
    }

    public LocalDate getDateFieldText(){
        return DateField.getValue();
    }

    public Button getChooseButton() {
        return ChooseButton;
    }
    private String chosenCity;

    //TODO: Fonction qui appelle la fenêtre ChooseDestination quand on clique sur le bouton choisir
    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        CityController cityController = CityController.getInstance(); //singleton
        chooseDestinationViewController = fxmlLoader.getController();
        chooseDestinationViewController.setDefineTripController(this);
        chooseDestinationViewController.chooseButtonManagement(stage);
        chooseDestinationViewController.setCityController(cityController);
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    public void changeChooseButtonText(){
        ChooseButton.setText(chooseDestinationViewController.getCurrentCity().toString());
    }

    @FXML
    private void handleAddPlaneStageButtonClick(ActionEvent event) throws IOException {
        TripStage stage = new PlaneStage(new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml")));
        StageVbox.getChildren().add(stage.getFxml().load());
        defineTripListenner.onClickAddPlaneButton(stage);
    }

    @FXML
    private void handleAddHotelStageButtonClick(ActionEvent event) throws IOException {
        TripStage stage = new HotelStage(new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml")));
        StageVbox.getChildren().add(stage.getFxml().load());
        defineTripListenner.onClickAddHotelButton(stage);
    }
}
