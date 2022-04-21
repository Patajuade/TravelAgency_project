package com.example.travelagency.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HotelStageController {

    SpinnerValueFactory spinnerValueFactoryNumberNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
    SpinnerValueFactory spinnerValueFactoryPriceNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);

    int numberOfNights;
    int pricePerNight;
    int price;

    @FXML
    private Label bottomInfoLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Spinner<Integer> numberOfNightsSpinner;

    @FXML
    private Spinner<Integer> pricePerNightSpinner;

    @FXML
    private Label topInformationLabel;

    @FXML
    private void handleNumberOfNightsSpinner(){
        numberOfNightsSpinner.setValueFactory(spinnerValueFactoryNumberNights);
        numberOfNights = numberOfNightsSpinner.getValue();
        calculatePricePerNight();
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        pricePerNightSpinner.setValueFactory(spinnerValueFactoryPriceNights);
        pricePerNight = pricePerNightSpinner.getValue();
        calculatePricePerNight();
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    private void updateLabel(Label label){
        label.setText(numberOfNights + " nuit(s) à l'hôtel pour " + price + " euros" );
    }

    private void calculatePricePerNight(){
        price = pricePerNight * numberOfNights;
    }


}
