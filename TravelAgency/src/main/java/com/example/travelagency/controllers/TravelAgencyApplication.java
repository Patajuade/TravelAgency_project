package com.example.travelagency.controllers;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main JavaFx controller class
 */
public class TravelAgencyApplication extends Application {

    TripsResumeController tripsResumeController;

    /**
     * Opening of the first window when the program is launched
     * @param stage is referring to the window
     * @throws IOException management of input/output exceptions.
     */
    @Override
    public void start(Stage stage)  throws IOException {
        tripsResumeController = new TripsResumeController();
        tripsResumeController.show();
    }

    public static void main(String[] args) {
        launch();
    }
}