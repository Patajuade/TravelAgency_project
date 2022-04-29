package com.example.travelagency.controllers;
import com.example.travelagency.models.*;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripController;
import com.example.travelagency.views.HotelStageController;
import com.example.travelagency.views.PlaneStageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class TravelAgencyApplication extends Application implements DefineTripController.Listener {

    ArrayList<TripStage> stages= new ArrayList<>();
    TripStage SourceOfTrip;
    CityModel CurrentStageCitySource;
    DefineTripController defineTripController;


    @Override
    public void start(Stage stage)  throws IOException {
        //TODO : Faire correctement les ancres de la fenêtre
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        defineTripController = fxmlLoader.getController();
        defineTripController.setListener(this);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


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
                //TODO : Remplacer la variable par le voyage.setCitySource
                CurrentStageCitySource = chooseDestinationViewController.getCurrentCity();
                defineTripController.changeStartCity(CurrentStageCitySource);
            }
        });
        chooseDestinationViewController.setCityController(cityController);
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    //TODO : Optimiser le MenuButton car il prend une fonction par MenuItem mais doit y avoir moyen avec un switch j'imagine

    @Override
    public void onClickAddPlaneButton() throws IOException {
        PlaneStage planeStage = new PlaneStage(new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml")));
        planeStage.setSource(CurrentStageCitySource);
        stages.add(planeStage);
        defineTripController.addStageToStageVBOX(planeStage);
        PlaneStageController planeStageController = planeStage.getFxml().getController();
        planeStageController.setPlaneStage(planeStage);
        planeStageController.setListener(new PlaneStageController.Listener() {
            @Override
            public void onChooseButtonClick() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                Stage stage = new Stage();
                CityController cityController = CityController.getInstance(); //singleton
                ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
                chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
                    @Override
                    public void selectedDestination() {
                        stage.close();
                        planeStage.setDestination(chooseDestinationViewController.getCurrentCity());
                        planeStage.distanceCompute();
                        planeStage.durationCompute();
                        planeStage.priceCompute();
                        planeStageController.changeButtonText();
                        planeStageController.updateLabels();
                    }
                });
                chooseDestinationViewController.setCityController(cityController);
                chooseDestinationViewController.showCities();
                stage.setTitle("Choisir une destination");
                stage.setScene(scene);
                stage.show();
            }

            @Override
            public void onRadioButton700Click() {
                planeStage.setFlyingSpeed(700);
                planeStage.durationCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onRadioButton900Click() {
                planeStage.setFlyingSpeed(900);
                planeStage.durationCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onUpperWaitingTimeSpinner() {

            }

            @Override
            public void onMenuItem0025Click() {
                planeStage.setPricePerKm(0.025);
                planeStage.priceCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onMenuItem00507Click() {
                planeStage.setPricePerKm(0.0507);
                planeStage.priceCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onMenuItem00758Click() {
                planeStage.setPricePerKm(0.0758);
                planeStage.priceCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onMenuItem01005Click() {
                planeStage.setPricePerKm(0.1005);
                planeStage.priceCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onMenuItem02Click() {
                planeStage.setPricePerKm(0.2);
                planeStage.priceCompute();
                planeStageController.updateLabels();
            }

            @Override
            public void onCloseButtonClick() {
                defineTripController.deleteStageOfStageVBOX(planeStage);
                stages.remove(planeStage);
            }
        });
    }

    @Override
    public void onClickAddHotelButton() throws IOException {
        TripStage tripStage = new HotelStage(new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml")));
        stages.add(tripStage);
        defineTripController.addStageToStageVBOX(tripStage);
        HotelStageController hotelStageController = tripStage.getFxml().getController();
        hotelStageController.setListener(new HotelStageController.Listener() {
            @Override
            public void onUpperNumberOfNightsSpinner() {
                int numberOfNights = hotelStageController.getNumberOfNightsSpinner().getValue();
                hotelStageController.getHotelStage().setNumberOfNights(numberOfNights);
                hotelStageController.updatePrice();
            }

            @Override
            public void onUpperPricePerNightsSpinner() {
                int pricePerNight = hotelStageController.getPricePerNightSpinner().getValue();
                hotelStageController.getHotelStage().setPricePerNight(pricePerNight);
                hotelStageController.calculatePricePerNight();
                hotelStageController.updatePrice();
            }

            @Override
            public void onKeyReleasedNumberOfNightsSpinner() {
                int numberOfNights = hotelStageController.getNumberOfNightsSpinner().getValue();
                hotelStageController.getHotelStage().setNumberOfNights(numberOfNights);
                hotelStageController.updatePrice();
            }

            @Override
            public void onKeyReleasedPricePerNightsSpinner() {
                int pricePerNight = hotelStageController.getPricePerNightSpinner().getValue();
                hotelStageController.getHotelStage().setPricePerNight(pricePerNight);
                hotelStageController.calculatePricePerNight();
                hotelStageController.updatePrice();
            }
        });
    }
}