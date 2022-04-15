package com.example.travelagency.views;

import com.example.travelagency.models.CityController;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.controllers.TravelAgencyApplication;
import com.example.travelagency.models.PlaneStage;
import com.example.travelagency.models.TripStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        System.out.println("button clicked!");
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

    private void handleAddPlaneStageButtonClick(ActionEvent event){
        ArrayList<TripStage> stages = null;
        TripStage stage1 = new PlaneStage();
        stages.add(stage1);
    }

    public void setStage(ArrayList<TripStage> stages) throws IOException {
        for(TripStage s : stages){
            FXMLLoader loader = new FXMLLoader(PlaneStage.class.getResource("PlaneStage.fxml"));
            AnchorPane pane = loader.load();
            StageVbox.getChildren().add(pane);
        }
    }

}
