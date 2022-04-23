package com.example.travelagency.views;
import com.example.travelagency.models.HotelStage;
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
    }

    @FXML
    private void handlePricePerNightsSpinner(){
        //TODO : remplacer le code ci-dessous par onUpperPricePerNightsSpinner
        listener.onUpperPricePerNightsSpinner();
    }

    @FXML
    private void handleClose(){
        //defineTripController.removeStage(hotelStage);
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
