package com.example.travelagency.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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

    public String getTripNameTextFieldText(){
        return TripNameTextField.getText();
    }

    public LocalDate getDateFieldText(){
        return DateField.getValue();
    }

    //TODO: Fonction qui appelle la fenêtre ChooseDestonation quand on clique sur le bouton choisir



}
