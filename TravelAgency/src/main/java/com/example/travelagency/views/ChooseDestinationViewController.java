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
import java.util.ResourceBundle;

public class ChooseDestinationViewController implements Initializable  {
    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;

    @FXML
    private Button ChooseDestinationButtonChoose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChooseDestinationButtonChoose.setOnAction(event -> setText("wouf"));
    }

    public String getDestinationText(){
        return ChooseDestinationTextField.getText();
    }

    public void setText(String string){
        ChooseDestinationTextField.setText(string);
    }
}