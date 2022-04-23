package com.example.travelagency.views;

import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.PlaneStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;

import java.io.IOException;

public class PlaneStageController {
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

    PlaneStage planeStage;

    public void setPlaneStage(PlaneStage planeStage) {
        this.planeStage = planeStage;
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
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

    public interface Listener {
        void onChooseButtonClick() throws IOException;
        void onRadioButton700Click();
        void onRadioButton900Click();
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
    private void handleWaitingTimeSpinner(ActionEvent event){

    }

    @FXML
    private void handleCloseButtonClick(ActionEvent event){

    }

}
