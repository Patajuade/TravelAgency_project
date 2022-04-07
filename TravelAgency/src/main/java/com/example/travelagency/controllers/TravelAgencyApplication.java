package com.example.travelagency.controllers;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelAgencyApplication extends Application  {
    ChooseDestinationViewController chooseDestinationViewController;
    CityController cityController;
    @Override
    public void start(Stage stage) throws IOException {

        //Essais fenêtre DefineTrip, qui doit appeller la fenêtre choose destination
        //TODO : Faire correctement les ancres de la fenêtre
        DefineTripController defineTripController;
        FXMLLoader fxmlLoader2 = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene test = new Scene(fxmlLoader2.load(), 513, 694);
        stage.setTitle("Définir son voyage");
        stage.setScene(test);
        stage.show();


    }



    public static void main(String[] args) {
        launch();
    }

}