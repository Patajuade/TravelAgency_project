package com.example.travelagency.controllers;
import com.example.travelagency.models.*;
import com.example.travelagency.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class TravelAgencyApplication extends Application implements TripsResumeViewController.Listener{

    TripsResume trips = new TripsResume();
    TripsResumeViewController tripsResumeViewController;
    DefineTripController defineTripController;

    @Override
    public void start(Stage stage)  throws IOException {
        FXMLLoader fxmlTripsResume = new FXMLLoader(TravelAgencyApplication.class.getResource("TripsResume.fxml"));
        Scene TripsResumeScene = new Scene(fxmlTripsResume.load());
        tripsResumeViewController = fxmlTripsResume.getController();
        tripsResumeViewController.setListener(this);
        stage.setTitle("Voyages");
        stage.setScene(TripsResumeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onClickCreateTripButton() throws IOException {
        TripResume tripResume = new TripResume();
        trips.addTripResume(tripResume);
        defineTripController = new DefineTripController(tripResume);
        defineTripController.show();
        defineTripController.setListener(new DefineTripController.Listener() {
            @Override
            public void isClosed() {
                FXMLLoader fxmlTripResume =  new FXMLLoader(TravelAgencyApplication.class.getResource("TripResume.fxml"));
                try {
                    tripsResumeViewController.addTripResumeToTripVbox(fxmlTripResume.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
                tripResumeViewController.setTripResume(tripResume);
                tripResumeViewController.setListener(new TripResumeViewController.Listener() {
                    @Override
                    public void onClickShowTripButton() throws IOException {
                        //TODO : Gérer la récupération d'un trip resume en fonction de sa fenêtre
                        defineTripController = new DefineTripController(tripResumeViewController.getTripResume());
                        defineTripController.show();
                    }
                    @Override
                    public void onClickDeleteTripButton() {
                        try {
                            //TODO : Gérer l'anchor pane en interne pour lié la fxml à son objet en mode remove puis on get l'objet et on dégage son fxml lié
                            tripsResumeViewController.removeTripResumeToTripVbox(fxmlTripResume.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        trips.removeTripResume(tripResume);
                    }
                });
                tripResume.calculateAll();
                tripResumeViewController.UpdateLabelData();
                tripResumeViewController.UpdateLabelFromDate(defineTripController.getDate());
                tripResumeViewController.UpdateNameTripLabel(defineTripController.getNameTrip());
                tripResumeViewController.UpdateCrossedCities();
            }
        });


    }

    public void updateTripSteps(TripResume tripResume){
        AtomicReference<CityModel> source = new AtomicReference<>(tripResume.getSource());
        tripResume.getStages().forEach(
                s -> {
                    if(s instanceof PlaneStage){
                        s.setSource(source.get());
                        source.set(s.getDestination());
                        ((PlaneStage)s).setDistance(s.getDestination().distanceCompute(s.getSource())); //comme la liste est polymorphique, on doit faire un cast de s en planestage
                        s.durationCompute();
                        s.priceCompute();
//                        PlaneStageViewController planeStageController = s.getFxml().getController();
//                        planeStageController.updateLabels();
                    }
                }
        );
    }

}