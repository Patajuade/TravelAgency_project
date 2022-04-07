package com.example.travelagency.controllers;

import com.example.travelagency.models.CityController;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.views.ChooseDestinationViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class travelAgencyApplication extends Application  {
    ChooseDestinationViewController controller;
    CityController cityController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(travelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        ChooseButtonManagement();
        cityController = CityController.getInstance(); //singleton
        cityController.init();
        controller.setCityController(cityController);
        controller.showAllCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    private void ChooseButtonManagement() {
        controller.setChooseDestinationListener(new ChooseDestinationViewController.ChooseDestinationListener() {
            @Override
            public CityModel selectedDestination() {
                return controller.getCurrentCity();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}