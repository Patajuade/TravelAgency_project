package com.example.travelagency.views;
import com.example.travelagency.models.HotelStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;

public class HotelStageController {

    SpinnerValueFactory spinnerValueFactoryNumberNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
    SpinnerValueFactory spinnerValueFactoryPriceNights = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5000);


    public HotelStage getHotelStage() {
        return hotelStage;
    }

    HotelStage hotelStage = new HotelStage(new FXMLLoader());
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onUpperNumberOfNightsSpinner();
        void onUpperPricePerNightsSpinner();
        void onKeyReleasedNumberOfNightsSpinner();
        void onKeyReleasedPricePerNightsSpinner();
        void onCloseButtonClick();
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
        getNumberOfNightsSpinner().setValueFactory(getSpinnerValueFactoryNumberNights());
        listener.onUpperNumberOfNightsSpinner();
        listener.onKeyReleasedNumberOfNightsSpinner();
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        getPricePerNightSpinner().setValueFactory(getSpinnerValueFactoryPriceNights());
        listener.onUpperPricePerNightsSpinner();
        listener.onKeyReleasedPricePerNightsSpinner();
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

    public void updatePrice(){
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }

    public SpinnerValueFactory getSpinnerValueFactoryNumberNights() {
        return spinnerValueFactoryNumberNights;
    }

    public SpinnerValueFactory getSpinnerValueFactoryPriceNights() {
        return spinnerValueFactoryPriceNights;
    }

    public Spinner<Integer> getNumberOfNightsSpinner() {
        return numberOfNightsSpinner;
    }

    public Spinner<Integer> getPricePerNightSpinner() {
        return pricePerNightSpinner;
    }
}
