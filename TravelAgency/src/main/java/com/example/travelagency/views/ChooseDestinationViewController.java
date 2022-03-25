package com.example.travelagency.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseDestinationViewController implements Initializable {
    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;

    @FXML
    protected Button ChooseDestinationButtonChoose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChooseDestinationButtonChoose.setOnAction(actionEvent -> {
            ChooseDestinationTextField.setText("lol");
        });
    }
}