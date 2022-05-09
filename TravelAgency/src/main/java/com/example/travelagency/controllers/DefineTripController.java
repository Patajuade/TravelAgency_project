package com.example.travelagency.controllers;

import com.example.travelagency.models.*;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripViewController;
import com.example.travelagency.views.HotelStageViewController;
import com.example.travelagency.views.PlaneStageViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DefineTripController implements DefineTripViewController.Listener{
    DefineTripViewController defineTripViewController;
    Stage defineTripStage;

    ChooseDestinationController chooseDestinationController;

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getDate() {
        return defineTripViewController.getDate();
    }

    public String getNameTrip() {
        return  defineTripViewController.getNameTrip();
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        DefineTripViewController defineTripViewController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripScene);
        stage.show();
        //stage.setOnCloseRequest();
    }
    public void onClose() {
        listener.isClosed();
        defineTripStage.close();
    }

    public interface Listener{
        void isClosed();
    }
    @Override
    public CityModel onClickChooseDestinationButton() throws IOException {
        chooseDestinationController.show();
        return chooseDestinationController.selectedDestination();
    }

    @Override
    public void onClickAddPlaneButton(TripResume tripResume) throws IOException {
        PlaneStage planeStage = new PlaneStage();
        planeStage.setSource(tripResume.getSource());
        tripResume.addStage(planeStage);
        FXMLLoader fxmlPlaneStage = new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml"));
        defineTripViewController.addStageToStageVBOX(fxmlPlaneStage.load());
        PlaneStageViewController planeStageViewController = fxmlPlaneStage.getController();
        planeStageViewController.setPlaneStage(planeStage);
        planeStageViewController.setListener(new PlaneStageViewController.Listener() {
            @Override
            public void onChooseButtonClick() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                Stage stage = new Stage();
                ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
                chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
                    @Override
                    public CityModel selectedDestination() {
                        stage.close();
                        planeStage.setDestination(chooseDestinationViewController.getCurrentCity());
                        planeStageViewController.changeButtonText();
                        //TODO : Foutre update trip step dans trip resume
                        //updateTripSteps(tripResume);
                        return null;
                    }
                });
                chooseDestinationViewController.setCityController(ManagementCity.getInstance());
                chooseDestinationViewController.showCities();
                stage.setTitle("Choisir une destination");
                stage.setScene(scene);
                stage.show();
            }

            @Override
            public void onRadioButton700Click() {
                planeStage.setFlyingSpeed(700);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onRadioButton900Click() {
                planeStage.setFlyingSpeed(900);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onUpperWaitingTimeSpinner() {
                int waitingTime = planeStageViewController.getWaitingTime();
                planeStageViewController.getPlaneStage().setWaitingTime(waitingTime);
                planeStageViewController.calculateDuration();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onMenuItem0025Click() {
                planeStage.setPricePerKm(0.025);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onMenuItem00507Click() {
                planeStage.setPricePerKm(0.0507);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onMenuItem00758Click() {
                planeStage.setPricePerKm(0.0758);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onMenuItem01005Click() {
                planeStage.setPricePerKm(0.1005);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onMenuItem02Click() {
                planeStage.setPricePerKm(0.2);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
            }

            @Override
            public void onCloseButtonClick() {
                try {
                    defineTripViewController.deleteStageOfStageVBOX(fxmlPlaneStage.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tripResume.removeStage(planeStage);
            }
        });
    }

    @Override
    public void onClickAddHotelButton(TripResume tripResume) throws IOException {
        {
            FXMLLoader fxmlHotelStage = new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml"));
            TripStage hotelStage = new HotelStage();
            tripResume.addStage(hotelStage);
            defineTripViewController.addStageToStageVBOX(fxmlHotelStage.load());
            HotelStageViewController hotelStageController = fxmlHotelStage.getController();
            hotelStageController.setListener(new HotelStageViewController.Listener() {
                @Override
                public void onUpperNumberOfNightsSpinner() {
                    int numberOfNights = hotelStageController.getNumberOfNights();
                    hotelStageController.getHotelStage().setNumberOfNights(numberOfNights);
                    hotelStageController.calculatePricePerNight();
                    hotelStageController.updateLabels();
                }

                @Override
                public void onUpperPricePerNightsSpinner() {
                    int pricePerNight = hotelStageController.getPricePerNights();
                    hotelStageController.getHotelStage().setPricePerNight(pricePerNight);
                    hotelStageController.calculatePricePerNight();
                    hotelStageController.updateLabels();
                }

                @Override
                public void onCloseButtonClick() {
                    try {
                        defineTripViewController.deleteStageOfStageVBOX(fxmlHotelStage.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tripResume.removeStage(hotelStage);
                }
            });
        }
    }
}
