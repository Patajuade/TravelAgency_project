package com.example.travelagency.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * View controller for TripsResume
 */
public class TripsResumeViewController {

    @FXML
    private Button createTripButton;

    @FXML
    private VBox tripVbox;

    private Listener listener;

    /**
     * Listener for the create trip button click
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    void createTripButtonClick() throws IOException {
        listener.onClickCreateTripButton();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Listener Interface
     */
    public interface Listener{
        void onClickCreateTripButton() throws IOException;
    }

    /**
     * Adds an anchor pane to the main trip resume Vbox
     * @param anchorPane is the anchor pane added
     * @throws IOException management of input/output exceptions.
     */
    public void addTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().add(anchorPane);
    }

    /**
     * Removes an anchor pane from the main trip resume Vbox
     * @param anchorPane is the anchor pane removed
     * @throws IOException management of input/output exceptions.
     */
    public void removeTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().remove(anchorPane);
    }
}
