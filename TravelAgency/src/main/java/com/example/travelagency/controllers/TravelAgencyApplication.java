package com.example.travelagency.controllers;
import com.example.travelagency.models.*;
import com.example.travelagency.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class TravelAgencyApplication extends Application {

    TripsResumeController tripsResumeController;
    TripResume editingTripResume;
    TripResumeViewController tripResumeViewController;

    @Override
    public void start(Stage stage)  throws IOException {
        tripsResumeController = new TripsResumeController();
        tripsResumeController.show();
    }

    public static void main(String[] args) {
        launch();
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