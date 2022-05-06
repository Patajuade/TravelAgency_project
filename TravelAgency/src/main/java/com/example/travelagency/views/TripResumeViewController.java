package com.example.travelagency.views;

import com.example.travelagency.models.TripResume;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class TripResumeViewController {

    @FXML
    private Label crossedCitiesLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label fromAndDateLabel;

    @FXML
    private Label nameTripLabel;

    @FXML
    private Label priceWaitingTimeDistanceLabel;

    @FXML
    private Button showButton;

    TripResume tripResume;

    public void setTripResume(TripResume tripResume) {
        this.tripResume = tripResume;
    }

    @FXML
    void deleteTripButtonClick(ActionEvent event) {
        listener.onClickDeleteTripButton();
    }

    @FXML
    void showTripButtonClick(ActionEvent event) throws IOException {
        listener.onClickShowTripButton();
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void onClickDeleteTripButton();
        void onClickShowTripButton() throws IOException;
    }
}