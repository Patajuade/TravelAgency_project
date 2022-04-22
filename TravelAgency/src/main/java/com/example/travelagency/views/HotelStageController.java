package com.example.travelagency.views;

import com.example.travelagency.controllers.TravelAgencyApplication;
import com.example.travelagency.models.HotelStage;
import com.example.travelagency.models.TripStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HotelStageController {

    SpinnerValueFactory spinnerValueFactoryNumberNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
    SpinnerValueFactory spinnerValueFactoryPriceNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);

    DefineTripController defineTripController;
    TripStage hotelStage = new HotelStage();
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onUpperNumberOfNightsSpinner();
        void onUpperPricePerNightsSpinner();
    }

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
        //TODO : remplacer le code ci-dessous par onUpperNumberOfNightsSpinner
        listener.onUpperNumberOfNightsSpinner();
        updatePrice();
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        //TODO : remplacer le code ci-dessous par onUpperPricePerNightsSpinner
        listener.onUpperPricePerNightsSpinner();
        updatePrice();
    }

    @FXML
    private void handleClose(){
        defineTripController.removeStage(hotelStage);
    }

    private void updateLabel(Label label){
        label.setText(hotelStage.getNumberOfNights() + " nuit(s) à l'hôtel pour " + hotelStage.getPrice() + " euros" );
    }

    private void calculatePricePerNight(){
        hotelStage.priceCompute();
    }

    private void updatePrice(){
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }
}
