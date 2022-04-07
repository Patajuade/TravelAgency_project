package com.example.travelagency.controllers;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripController;
import com.example.travelagency.views.PlaneStageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelAgencyApplication extends Application  {
    ChooseDestinationViewController controller;
    CityController cityController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        ChooseButtonManagement(stage);
        cityController = CityController.getInstance(); //singleton
        cityController.init();
        controller.setCityController(cityController);
        controller.showAllCities();
        stage.setTitle("Choisir une destination");
        //stage.setScene(scene);
        //stage.show();

        //Essais fenêtre DefineTrip, qui doit appeller la fenêtre choose destination
        //TODO : Faire correctement les ancres de la fenêtre
        DefineTripController defineTripController;
        FXMLLoader fxmlLoader2 = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene test = new Scene(fxmlLoader2.load(), 513, 694);
        stage.setTitle("Définir son voyage");
        stage.setScene(test);
        stage.show();


    }

    private void ChooseButtonManagement(Stage stage) {
        controller.setChooseDestinationListener(new ChooseDestinationViewController.ChooseDestinationListener() {
            @Override
            public CityModel selectedDestination() {
                stage.close();
                return controller.getCurrentCity();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}