package com.example.travelagency.views;

import com.example.travelagency.models.HotelStage;
import com.example.travelagency.models.PlaneStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class PlaneStageViewController {
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
    private Spinner<Integer> WaitingTimeSpinner;

    //PlaneStage planeStage;
    PlaneStage planeStage = new PlaneStage(new FXMLLoader());

    private Listener listener;

    SpinnerValueFactory spinnerValueFactoryWaitingTime = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);

    public Spinner<Integer> getWaitingTimeSpinner() {
        return WaitingTimeSpinner;
    }

    public PlaneStage getPlaneStage() {
        return planeStage;
    }

    public SpinnerValueFactory getSpinnerValueFactoryWaitingTime() { return spinnerValueFactoryWaitingTime; }

    public void setPlaneStage(PlaneStage planeStage) {
        this.planeStage = planeStage;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
        WaitingTimeSpinner.valueProperty().addListener((observer, oldvalue, newvalue)->{
            handleWaitingTimeSpinner();
        });
    }


    public void changeButtonText() {
        ChooseButton.setText(planeStage.getDestination().getCityName()+ "(" + planeStage.getDestination().getCountryName() + ")");
    }

    public void updateLabels() {
        updateTopLabel();
        updateBottomLabel();
    }

    private void updateTopLabel() {
        if(planeStage.getDestination() != null && planeStage.getSource() != null){
            TopInformationLabel.setText("Voyage en avion (" + planeStage.getDestination().getCityName() + ","
                    + planeStage.getDistance() + "km," + planeStage.getDuration() + "heures,"
                    + planeStage.getPrice() +"euros)");
        }
    }

    private void updateBottomLabel() {
        if(planeStage.getDestination() != null && planeStage.getSource() != null) {
            BottomInformationLabel.setText(planeStage.getDistance() + "km," + planeStage.getDuration() + "heures," + planeStage.getPrice() + "euros");
        }
    }

    public void calculateDuration(){
        planeStage.durationCompute();
    }

    public interface Listener {
        void onChooseButtonClick() throws IOException;
        void onRadioButton700Click();
        void onRadioButton900Click();
        void onUpperWaitingTimeSpinner();
        void onMenuItem0025Click();
        void onMenuItem00507Click();
        void onMenuItem00758Click();
        void onMenuItem01005Click();
        void onMenuItem02Click();
        void onCloseButtonClick();
    }

    @FXML
    private void handleWaitingTimeSpinner(){
        getWaitingTimeSpinner().setValueFactory(getSpinnerValueFactoryWaitingTime());
        listener.onUpperWaitingTimeSpinner();
        //TODO : régler le souci : OnKeyReleased fonctionne pas
//        listener.onKeyReleasedWaitingTimeSpinner();
    }

    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        listener.onChooseButtonClick();
    }

    @FXML
    private void handleRadioButton700Click(ActionEvent event){
        listener.onRadioButton700Click();
    }

    @FXML
    private void handleRadioButton900Click(ActionEvent event){
        listener.onRadioButton900Click();
    }

    @FXML
    private void handleChooseMenuButton(ActionEvent event){

    }

    @FXML
    void handleMenuItem0025(ActionEvent event) {
        listener.onMenuItem0025Click();
        PricePerKmMenuButton.setText("0.025");
    }

    @FXML
    void handleMenuItem00507(ActionEvent event) {
        listener.onMenuItem00507Click();
        PricePerKmMenuButton.setText("0.0507");
    }

    @FXML
    void handleMenuItem00758(ActionEvent event) {
        listener.onMenuItem00758Click();
        PricePerKmMenuButton.setText("0.0758");
    }

    @FXML
    void handleMenuItem01005(ActionEvent event) {
        listener.onMenuItem01005Click();
        PricePerKmMenuButton.setText("0.1005");
    }

    @FXML
    void handleMenuItem02(ActionEvent event) {
        listener.onMenuItem02Click();
        PricePerKmMenuButton.setText("0.2");
    }

    @FXML
    private void handleCloseButtonClick(ActionEvent event){
        listener.onCloseButtonClick();
    }

}
