package com.example.travelagency.controllers;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelAgencyApplication extends Application {

    TripsResumeController tripsResumeController;

    @Override
    public void start(Stage stage)  throws IOException {
        tripsResumeController = new TripsResumeController();
        tripsResumeController.show();
    }

    public static void main(String[] args) {
        launch();
    }
}