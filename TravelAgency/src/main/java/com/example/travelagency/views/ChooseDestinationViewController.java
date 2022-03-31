package com.example.travelagency.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseDestinationViewController    {
    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;
    @FXML
    private Button ChooseDestinationButton;
    @FXML
    void onChooseDestinationButtonClick(ActionEvent event) {
        listener.onChooseDestinationButtonClick();
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void onChooseDestinationButtonClick();
    }

    public void setText(String string){
        ChooseDestinationTextField.setText(string);
    }

    public void showCities(){

    }
}