package com.example.travelagency.controllers;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import com.example.travelagency.views.ChooseDestinationViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class travelAgencyApplication extends Application  {
    ChooseDestinationViewController controller;
    CityController cityController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(travelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        ChooseButtonManagement(stage);
        cityController = CityController.getInstance(); //singleton
        cityController.init();
        controller.setCityController(cityController);
        controller.showAllCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
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