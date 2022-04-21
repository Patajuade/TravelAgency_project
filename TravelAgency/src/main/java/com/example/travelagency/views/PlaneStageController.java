package com.example.travelagency.views;

import com.example.travelagency.controllers.TravelAgencyApplication;
import com.example.travelagency.interfaces.PlaneStageListener;
import com.example.travelagency.models.CityController;
import com.example.travelagency.models.PlaneStage;
import com.example.travelagency.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaneStageController {
    PlaneStage stage = new PlaneStage(new FXMLLoader());
    ChooseDestinationViewController chooseDestinationViewController;
    DefineTripController defineTripController;
    @FXML
    private Label BottomInformationLabel;

    @FXML
    private Button ChooseButton;

    @FXML
    private Button CloseButton;

    @FXML
    private MenuButton PricePerKmMenuButton;

    @FXML
    private RadioButton RadioButton700;

    @FXML
    private RadioButton RadioButton900;

    @FXML
    private Label TopInformationLabel;

    @FXML
    private Spinner<?> WaitingTimeSpinner;

    public void setPlaneStageListener(PlaneStageListener planeStageListener) {
        this.planeStageListener = planeStageListener;
    }

    private PlaneStageListener planeStageListener;

    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        CityController cityController = CityController.getInstance(); //singleton
        chooseDestinationViewController = fxmlLoader.getController();
        //chooseDestinationViewController.setDefineTripController();
        chooseDestinationViewController.chooseButtonManagement(stage);
        chooseDestinationViewController.setCityController(cityController);
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleRadioButton700Click(ActionEvent event){
        stage.setFlyingSpeed(700);
        if(RadioButton700.isArmed()){
            RadioButton900.();
        }
    }

    @FXML
    private void handleRadioButton900Click(ActionEvent event){
        stage.setFlyingSpeed(900);
    }

    @FXML
    private void handleChooseMenuButton(ActionEvent event){

    }

    @FXML
    private void handleWaitingTimeSpinner(ActionEvent event){

    }

    @FXML
    private void handleCloseButtonClick(ActionEvent event){

        planeStageListener.onClickCloseButton(stage);
    }

}
