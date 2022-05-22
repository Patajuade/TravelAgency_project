package com.example.travelagency.controllers;

import com.example.travelagency.models.TripResume;
import com.example.travelagency.models.TripsResume;
import com.example.travelagency.views.TripResumeViewController;
import com.example.travelagency.views.TripsResumeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View controller for TripsResume window
 */
public class TripsResumeController implements TripsResumeViewController.Listener{
    TripsResumeViewController tripsResumeViewController;
    Stage stage;
    TripsResume trips = new TripsResume();
    TripResume editingTripResume;


    /**
     * Opens a DefineTrip window on CreateTripButton click
     * @throws IOException management of input/output exceptions.
     */
    @Override
    public void onClickCreateTripButton() throws IOException {
        stage.close();
        TripResume tripResume = new TripResume();
        trips.addTripResume(tripResume);
        DefineTripController defineTripController = new DefineTripController();
        defineTripController.setTripResume(tripResume);
        defineTripController.show();
        managementTripResumeFxml(tripResume, defineTripController);
    }

    /**
     * Creates a TripResume anchor pane into the TripsResume main window
     * Gets its data from the DefineTrip window
     * @param tripResume
     * @param defineTripController
     * @throws IOException management of input/output exceptions.
     */
    private void managementTripResumeFxml(TripResume tripResume, DefineTripController defineTripController) throws IOException {
        FXMLLoader fxmlTripResume =  new FXMLLoader(TravelAgencyApplication.class.getResource("TripResume.fxml"));
        AnchorPane anchorPane = fxmlTripResume.load();
        tripsResumeViewController.addTripResumeToTripVbox(anchorPane);
        TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
        tripResumeViewController.setTripResume(tripResume);
        defineTripController.setListener(new DefineTripController.Listener() {
            /**
             * When the DefineTrip window is closed, data is updated into TripResume window.
             * @throws IOException management of input/output exceptions.
             */
            @Override
            public void isClosed() throws IOException {
                stage.show();
                editingTripResume = defineTripController.getTripResume();
                editingTripResume.calculateAll();
                editingTripResume.setDate(defineTripController.getDate());
                editingTripResume.setName(defineTripController.getNameTrip());
                tripResumeViewController.UpdateDatas(defineTripController.getDateAsString(), defineTripController.getNameTrip());
            }
        });
        tripResumeViewController.setListener(new TripResumeViewController.Listener() {
            /**
             * Closes TripResume and opens the DefineTrip window selected
             * @throws IOException management of input/output exceptions.
             */
            @Override
            public void onClickShowTripButton() throws IOException {
                stage.close();
                defineTripController.show();
            }

            /**
             * Deletes the TripResume anchor pane selected
             * @throws IOException management of input/output exceptions.
             */
            @Override
            public void onClickDeleteTripButton() throws IOException {
                tripsResumeViewController.removeTripResumeToTripVbox(anchorPane);
                trips.removeTripResume(tripResume);
            }
        });
    }

    /**
     * Opens the main TripsResume window
     * @throws IOException management of input/output exceptions.
     */
    public void show() throws IOException{
        stage = new Stage();
        stage.setMinWidth(650);
        stage.setMinHeight(600);
        FXMLLoader fxmlTripsResume = new FXMLLoader(TravelAgencyApplication.class.getResource("TripsResume.fxml"));
        Scene TripsResumeScene = new Scene(fxmlTripsResume.load());
        tripsResumeViewController = fxmlTripsResume.getController();
        tripsResumeViewController.setListener(this);
        stage.setTitle("Voyages");
        stage.setScene(TripsResumeScene);
        stage.show();
    }
}
