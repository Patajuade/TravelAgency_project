package com.example.travelagency.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TripsResumeViewController {

    @FXML
    private Button createTripButton;

    @FXML
    private VBox tripVbox;

    @FXML
    void createTripButtonClick(ActionEvent event) throws IOException {
        listener.onClickCreateTripButton();
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void onClickCreateTripButton() throws IOException;
    }

    public void addTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().add(anchorPane);
    }

    public void removeTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().remove(anchorPane);
    }
}
