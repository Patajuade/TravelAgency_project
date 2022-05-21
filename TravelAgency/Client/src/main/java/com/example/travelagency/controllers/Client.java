package com.example.travelagency.controllers;
import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import be.helha.common.networks.ObjectSocket;
import com.example.travelagency.views.TripResumeViewController;
import com.example.travelagency.views.TripsResumeViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class Client extends Application implements TripsResumeViewController.Listener {

    TripsResumeViewController tripsResumeViewController;
    TripsResume trips = new TripsResume();
    TripResume editingTripResume;
    Stage currentStage;
    private ObjectSocket objectSocket;
    public static final int port = 1099;

    @Override
    public void start(Stage stage) throws IOException {
        onTryToConnect();
        updateAllTrips();
        tripsResumeViewController = showFMXL(stage,TripsResumeViewController.class.getResource("TripsResume.fxml"));
        tripsResumeViewController.setListener(this);
        stage.setTitle("Voyages");
        currentStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public void onTryToConnect(){
        try {
            this.objectSocket = new ObjectSocket(new Socket("localhost", port));
        } catch (IOException e) {
            System.out.printf("Connection impossible");
        }
    }

    @Override
    public void onClickCreateTripButton() throws IOException {
        currentStage.close();
        TripResume tripResume = new TripResume();
        trips.addTripResume(tripResume);
        DefineTripController defineTripController = new DefineTripController();
        defineTripController.setTripResume(tripResume);
        defineTripController.show();
        managementTripResumeFxml(tripResume, defineTripController);
    }

    private void managementTripResumeFxml(TripResume tripResume, DefineTripController defineTripController) throws IOException {
        FXMLLoader fxmlTripResume =  new FXMLLoader(TripResumeViewController.class.getResource("TripResume.fxml"));
        AnchorPane anchorPane = fxmlTripResume.load();
        TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
        tripsResumeViewController.addTripResumeToTripVbox(anchorPane);
        tripResumeViewController.setTripResume(tripResume);
        defineTripController.setListener(new DefineTripController.Listener() {
            @Override
            public void isClosed() throws IOException {
                currentStage.show();
                editingTripResume = defineTripController.getTripResume();
                editingTripResume.calculateAll();
                editingTripResume.setDate(defineTripController.getDate());
                editingTripResume.setName(defineTripController.getNameTrip());
                tripResumeViewController.UpdateDatas(defineTripController.getDateAsString(), defineTripController.getNameTrip());
                objectSocket.write(trips);
            }
        });
        tripResumeViewController.setListener(new TripResumeViewController.Listener() {
            @Override
            public void onClickShowTripButton() throws IOException {
                currentStage.close();
                defineTripController.show();
            }
            @Override
            public void onClickDeleteTripButton() throws IOException {
                tripsResumeViewController.removeTripResumeToTripVbox(anchorPane);
                trips.removeTripResume(tripResume);
            }
        });
    }

    public void updateAllTrips() throws IOException {
        for(TripResume trip : trips.getTrips()){
            DefineTripController defineTripController = new DefineTripController();
            defineTripController.setTripResume(trip);
            managementTripResumeFxml(trip,defineTripController);
        }
    }

    private <T> T showFMXL(Stage stage, URL url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load());
        T controller = fxmlLoader.getController();
        stage.setMinWidth(650);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
        return controller;
    }
}