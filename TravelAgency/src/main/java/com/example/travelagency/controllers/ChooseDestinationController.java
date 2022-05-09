package com.example.travelagency.controllers;

import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.ManagementCity;
import com.example.travelagency.views.ChooseDestinationViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseDestinationController implements ChooseDestinationViewController.Listener {


    ChooseDestinationViewController chooseDestinationViewController;
    @Override
    public CityModel selectedDestination() {
        return chooseDestinationViewController.getCurrentCity();
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        ManagementCity cityController = ManagementCity.getInstance(); //singleton
        ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
        chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
            @Override
            public CityModel selectedDestination() {
                stage.close();
                return chooseDestinationViewController.getCurrentCity();
            }
        });
        chooseDestinationViewController.setCityController(cityController);
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

}
