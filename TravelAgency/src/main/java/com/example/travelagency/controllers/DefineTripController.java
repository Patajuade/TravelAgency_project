package com.example.travelagency.controllers;

import com.example.travelagency.models.*;
import com.example.travelagency.views.ChooseDestinationViewController;
import com.example.travelagency.views.DefineTripViewController;
import com.example.travelagency.views.HotelStageViewController;
import com.example.travelagency.views.PlaneStageViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDate;

public class DefineTripController implements DefineTripViewController.Listener{
    DefineTripViewController defineTripViewController;

    public void setTripResume(TripResume tripResume) throws IOException {
        this.tripResume = tripResume;
    }

    Stage stage;

    public TripResume getTripResume() {
        return tripResume;
    }

    TripResume tripResume;
    FXMLLoader fxmlLoader;

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String  getDateAsString() {
        return defineTripViewController.getDateAsString();
    }

    public String getNameTrip() {
        return  defineTripViewController.getNameTrip();
    }


    public void show() throws IOException {
        createDefineTripFxml();
        updateDatas();
        updateStages();
    }

    private void updateDatas() {
        defineTripViewController.setTripResume(tripResume);
        defineTripViewController.UpdateLabel(tripResume.getTotalDistance(),tripResume.getTotalTime(),tripResume.getTotalPrice());
        defineTripViewController.updateDatas();
    }

    private void updateStages() throws IOException {
        tripResume.updateTripStep();
        for(TripStage tripStage : tripResume.getStages()){
            if(tripStage instanceof PlaneStage){
                //TODO:AFFICHER ETAPE AVION Potentiellement un controleur en plus ...
                FXMLLoader fxmlPlaneStage = new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml"));
                AnchorPane anchorPane = fxmlPlaneStage.load();
                defineTripViewController.addStageToStageVBOX(anchorPane);
            }
            else if(tripStage instanceof HotelStage){
                //TODO:AFFICHER ETAPE HOTEL
                FXMLLoader fxmlHotelStage = new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml"));
                AnchorPane anchorPane = fxmlHotelStage.load();
                defineTripViewController.addStageToStageVBOX(anchorPane);
            }
        }
    }

    private void createDefineTripFxml() throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        defineTripViewController = fxmlLoader.getController();
        defineTripViewController.setListener(this);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripScene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    listener.isClosed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LocalDate getDate() {
        return defineTripViewController.getDate();
    }

    public interface Listener{
        void isClosed() throws IOException;
    }

    @Override
    public void onClickChooseDestinationButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
        chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
            @Override
            public void selectedDestination() {
                stage.close();
                //TODO : Foutre update trip step dans trip resume
                tripResume.updateTripStep();
                tripResume.setSource(chooseDestinationViewController.getCurrentCity());
                defineTripViewController.changeStartCity(chooseDestinationViewController.getCurrentCity());
            }
        });
        chooseDestinationViewController.setCityController(ManagementCity.getInstance());
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void onClickAddPlaneButton() throws IOException {
        PlaneStage planeStage = new PlaneStage();
        planeStage.setSource(tripResume.getSource());
        tripResume.addStage(planeStage);
        FXMLLoader fxmlPlaneStage = new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml"));
        AnchorPane anchorPane = fxmlPlaneStage.load();
        defineTripViewController.addStageToStageVBOX(anchorPane);
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
                    public void selectedDestination() {
                        stage.close();
                        planeStage.setDestination(chooseDestinationViewController.getCurrentCity());
                        planeStageViewController.changeButtonText();
                        //TODO : Faire le calcul des villes en dessous c'est pourri c'est pour du test
                        //planeStage.setDistance(planeStage.getDestination().distanceCompute(tripResume.getSource()));
                        tripResume.updateTripStep();

                        planeStageViewController.updateLabels();
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
                    defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tripResume.removeStage(planeStage);
            }
        });
    }

    @Override
    public void onClickAddHotelButton() throws IOException {
        {
            TripStage hotelStage = new HotelStage();
            tripResume.addStage(hotelStage);
            FXMLLoader fxmlHotelStage = new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml"));
            AnchorPane anchorPane = fxmlHotelStage.load();
            defineTripViewController.addStageToStageVBOX(anchorPane);
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
                        defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tripResume.removeStage(hotelStage);
                }
            });
        }
    }
}
