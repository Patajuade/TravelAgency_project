package com.example.travelagency.controllers;
import com.example.travelagency.views.DefineTripController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TravelAgencyApplication extends Application  {

    DefineTripController defineTripController;
    @Override
    public void start(Stage stage) throws IOException {
        //TODO : Faire correctement les ancres de la fenêtre
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripCcene = new Scene(fxmlLoader.load(), 513, 694);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripCcene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}