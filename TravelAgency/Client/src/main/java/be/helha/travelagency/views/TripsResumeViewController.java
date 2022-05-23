package be.helha.travelagency.views;

import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * View controller for TripsResume window
 */
public class TripsResumeViewController {

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
     * Clears the tripResume window Vbox
     */
    public void cleanVbox() {
        tripVbox.getChildren().clear();
    }

    /**
     * Listener interface
     * Contains action functions
     */
    public interface Listener{
        /**
         * Action when CreateTripButton is clicked
         * @throws IOException management of input/output exceptions.
         */
        void onClickCreateTripButton() throws IOException;
    }

    /**
     * Adds an anchor pane to the main trip resume Vbox
     * TripVbox is the vbox in which the TripResume anchor panes are going
     * @param anchorPane is the anchor pane added
     * @throws IOException management of input/output exceptions.
     */
    public void addTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().add(anchorPane);
    }

    /**
     * Removes an anchor pane from the main trip resume Vbox
     * TripVbox is the vbox in which the TripResume anchor panes are going
     * @param anchorPane is the anchor pane removed
     * @throws IOException management of input/output exceptions.
     */
    public void removeTripResumeToTripVbox(AnchorPane anchorPane) throws IOException {
        tripVbox.getChildren().remove(anchorPane);
    }
}
