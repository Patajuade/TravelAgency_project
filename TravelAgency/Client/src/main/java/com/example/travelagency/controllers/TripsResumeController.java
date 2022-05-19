package com.example.travelagency.controllers;

import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import com.example.travelagency.views.TripResumeViewController;
import com.example.travelagency.views.TripsResumeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TripsResumeController implements TripsResumeViewController.Listener{
    TripsResumeViewController tripsResumeViewController;
    Stage stage;
    TripsResume trips = new TripsResume();
    TripResume editingTripResume;
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

    private void managementTripResumeFxml(TripResume tripResume, DefineTripController defineTripController) throws IOException {
        FXMLLoader fxmlTripResume =  new FXMLLoader(TravelAgencyApplication.class.getResource("TripResume.fxml"));
        AnchorPane anchorPane = fxmlTripResume.load();
        tripsResumeViewController.addTripResumeToTripVbox(anchorPane);
        TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
        tripResumeViewController.setTripResume(tripResume);
        defineTripController.setListener(new DefineTripController.Listener() {
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
            @Override
            public void onClickShowTripButton() throws IOException {
                stage.close();
                defineTripController.show();
            }
            @Override
            public void onClickDeleteTripButton() throws IOException {
                tripsResumeViewController.removeTripResumeToTripVbox(anchorPane);
                trips.removeTripResume(tripResume);
            }
        });
    }

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
