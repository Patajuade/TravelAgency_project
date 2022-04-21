package com.example.travelagency.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HotelStageController {

    SpinnerValueFactory spinnerValueFactoryNumberNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
    SpinnerValueFactory spinnerValueFactoryPriceNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);

    @FXML
    private Label BottomInformationLabel;

    @FXML
    private Button CloseButton;

    @FXML
    private Spinner<Integer> NumberOfNightsSpinner;

    @FXML
    private Spinner<Integer> PricePerNigthSpinner;

    @FXML
    private Label TopInformationLabel;

    @FXML
    private void handleNumberOfNightsSpinner(){
        NumberOfNightsSpinner.setValueFactory(spinnerValueFactoryNumberNights);
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        PricePerNigthSpinner.setValueFactory(spinnerValueFactoryPriceNights);
    }

}
