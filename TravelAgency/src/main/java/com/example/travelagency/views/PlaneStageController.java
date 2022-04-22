package com.example.travelagency.views;

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

    private ChooseDestinationViewController.Listener listener;

    public void setListener(ChooseDestinationViewController.Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onChooseButtonClick();
        void onRadioButton700Click();
        void onRadioButton900Click();
    }
    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
    }

    @FXML
    private void handleRadioButton700Click(ActionEvent event){

    }

    @FXML
    private void handleRadioButton900Click(ActionEvent event){

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
