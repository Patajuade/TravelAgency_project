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