package com.example.travelagency.controllers;
import com.example.travelagency.interfaces.DefineTripListener;
import com.example.travelagency.interfaces.PlaneStageListener;
import com.example.travelagency.models.TripStage;
import com.example.travelagency.views.DefineTripController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class TravelAgencyApplication extends Application implements DefineTripListener, PlaneStageListener {

    ArrayList<TripStage> stages= new ArrayList<>();
    @Override
    public void start(Stage stage)  throws IOException {
        //TODO : Faire correctement les ancres de la fenêtre
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripCcene = new Scene(fxmlLoader.load(), 900, 800);
        DefineTripController controller = fxmlLoader.getController();
        controller.setDefineTripListenner(this);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripCcene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onClickChooseDestinationButton() {

    }

    @Override
    public void onClickAddPlaneButton(TripStage stage) {
        stages.add(stage);
    }

    @Override
    public void onClickAddHotelButton(TripStage stage) {
        stages.add(stage);
    }

    @Override
    public void onClickCloseButton(TripStage stage) {
        stages.remove(stage);
    }
}