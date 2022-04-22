package com.example.travelagency.controllers;
import com.example.travelagency.models.CityController;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.TripStage;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class TravelAgencyApplication extends Application implements DefineTripController.Listener{

    ArrayList<TripStage> stages= new ArrayList<>();
    TripStage SourceOfTrip;
    CityModel CurrentCity;
    DefineTripController defineTripController;

    @Override
    public void start(Stage stage)  throws IOException {
        //TODO : Faire correctement les ancres de la fenêtre
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripCcene = new Scene(fxmlLoader.load(), 900, 800);
        defineTripController = fxmlLoader.getController();
        defineTripController.setListener(this);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripCcene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onClickChooseDestinationButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        CityController cityController = CityController.getInstance(); //singleton
        ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
        chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
            @Override
            public void selectedDestination() {
                stage.close();
                CurrentCity = chooseDestinationViewController.getCurrentCity();
                defineTripController.changeStartCity(CurrentCity);
            }
        });
        chooseDestinationViewController.setCityController(cityController);
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void onClickAddPlaneButton(TripStage stage) {
        stages.add(stage);
    }

    @Override
    public void onClickAddHotelButton(TripStage stage) {
        stages.add(stage);
    }

}