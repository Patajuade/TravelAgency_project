package com.example.travelagency.views;
import com.example.travelagency.models.HotelStage;
import com.example.travelagency.models.IViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelStageViewController implements Initializable, IViewController {

    public HotelStage getHotelStage() {
        return hotelStage;
    }

    HotelStage hotelStage = new HotelStage();
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void update() {
        updateLabels();
        updateComponent();
    }

    private void updateComponent() {
        numberOfNightsSpinner.getEditor().setText(String.valueOf(hotelStage.getNumberOfNights()));
        pricePerNightSpinner.getEditor().setText(String.valueOf(hotelStage.getPricePerNight()));
    }

    public interface Listener {
        void onUpperNumberOfNightsSpinner();
        void onUpperPricePerNightsSpinner();
        void onCloseButtonClick();
    }
    public int getNumberOfNights(){
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(numberOfNightsSpinner.getEditor().getText())));
        }catch(NumberFormatException e) {
            return 0;
        }
    }
    public int getPricePerNights(){
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(pricePerNightSpinner.getEditor().getText())));
        }catch(NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfNightsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        numberOfNightsSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleNumberOfNightsSpinner();
        }));
        pricePerNightSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        pricePerNightSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleNumberOfNightsSpinner();
        }));
    }

    @FXML
    private Label bottomInfoLabel;

    @FXML
    private Spinner<Integer> numberOfNightsSpinner;

    @FXML
    private Spinner<Integer> pricePerNightSpinner;

    @FXML
    private Label topInformationLabel;

    @FXML
    private void handleNumberOfNightsSpinner(){
        listener.onUpperNumberOfNightsSpinner();
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        listener.onUpperPricePerNightsSpinner();
    }

    @FXML
    private void handleClose(){
        listener.onCloseButtonClick();
    }

    private void updateLabel(Label label){
        label.setText(hotelStage.getNumberOfNights() + " nuit(s) à l'hôtel pour " + hotelStage.getPrice() + " euros" );
    }

    public void calculatePricePerNight(){
        hotelStage.priceCompute();
    }

    public void calculateDuration(int numberOfNights){
        hotelStage.setNumberOfNights(numberOfNights);
        hotelStage.durationCompute();
    }

    public void updateLabels(){
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }
}
