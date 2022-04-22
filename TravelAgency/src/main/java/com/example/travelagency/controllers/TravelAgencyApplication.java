package com.example.travelagency.controllers;
import com.example.travelagency.models.HotelStage;
import com.example.travelagency.views.DefineTripController;
import com.example.travelagency.views.HotelStageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TravelAgencyApplication extends Application implements HotelStageController.Listener {

    DefineTripController defineTripController;
    HotelStageController hotelStageController;
    HotelStage testStage;

    @Override
    public void start(Stage stage) throws IOException {
        //TODO : Faire correctement les ancres de la fenêtre
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripCcene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripCcene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onUpperNumberOfNightsSpinner() {    //Listener de HotelStageController
        hotelStageController.setListener(this);
        numberOfNightsSpinner.setValueFactory(spinnerValueFactoryNumberNights);
        int numberOfNights = numberOfNightsSpinner.getValue();
        hotelStage.setNumberOfNights(numberOfNights);
        updatePrice();
    }

    @Override
    public void onUpperPricePerNightsSpinner() {    //Listener de HotelStageController
        hotelStageController.setListener(this);
        pricePerNightSpinner.setValueFactory(spinnerValueFactoryPriceNights);
        int pricePerNight = pricePerNightSpinner.getValue();
        hotelStage.setPricePerNight(pricePerNight);
        calculatePricePerNight();
    }
}