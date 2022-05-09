package com.example.travelagency.controllers;
import com.example.travelagency.models.*;
import com.example.travelagency.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class TravelAgencyApplication extends Application implements TripsResumeViewController.Listener{

    TripsResume trips = new TripsResume();
    TripsResumeViewController tripsResumeViewController;

    @Override
    public void start(Stage stage)  throws IOException {
        FXMLLoader fxmlTripsResume = new FXMLLoader(TravelAgencyApplication.class.getResource("TripsResume.fxml"));
        Scene TripsResumeScene = new Scene(fxmlTripsResume.load());
        tripsResumeViewController = fxmlTripsResume.getController();
        tripsResumeViewController.setListener(this);
        stage.setTitle("Voyages");
        stage.setScene(TripsResumeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onClickCreateTripButton() throws IOException {
        TripResume tripResume = new TripResume();
        trips.addTripResume(tripResume);
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        DefineTripViewController defineTripController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripScene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
                    FXMLLoader fxmlTripResume =  new FXMLLoader(TravelAgencyApplication.class.getResource("TripResume.fxml"));
                    try {
                        tripsResumeViewController.addTripResumeToTripVbox(fxmlTripResume.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
                    tripResumeViewController.setTripResume(tripResume);
                    tripResumeViewController.setListener(new TripResumeViewController.Listener() {
                        @Override
                        public void onClickShowTripButton() throws IOException {
                           //TODO : Gérer la récupération d'un trip resume en fonction de sa fenêtre
                            tripResumeViewController.getTripResume();
                        }
                        @Override
                        public void onClickDeleteTripButton() {
                            try {
                                //TODO : Gérer l'anchor pane en interne pour lié la fxml à son objet en mode remove puis on get l'objet et on dégage son fxml lié
                                tripsResumeViewController.removeTripResumeToTripVbox(fxmlTripResume.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            trips.removeTripResume(tripResume);
                        }
                    });
                    tripResume.calculateAll();
                    tripResumeViewController.UpdateLabelData(tripResume.getTotalDistance(),tripResume.getTotalTime(),tripResume.getTotalPrice());
                    tripResumeViewController.UpdateLabelFromDate(tripResume.getSource(), defineTripController.getDate());
                    tripResumeViewController.UpdateNameTripLabel(defineTripController.getNameTrip());
                    tripResumeViewController.UpdateCrossedCities(tripResume.getStages());
                }
        );
        defineTripController.setListener(new DefineTripViewController.Listener() {
            @Override
            public void onClickChooseDestinationButton() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("ChooseDestination.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                Stage stage = new Stage();
                ManagementCity cityController = ManagementCity.getInstance(); //singleton
                ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
                chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
                    @Override
                    public void selectedDestination() {
                        stage.close();
                        tripResume.setSource(chooseDestinationViewController.getCurrentCity());
                        defineTripController.changeStartCity(tripResume.getSource());
                        updateTripSteps(tripResume);
                    }
                });
                chooseDestinationViewController.setCityController(cityController);
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
                defineTripController.addStageToStageVBOX(fxmlPlaneStage.load());
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
                                updateTripSteps(tripResume);
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
                            defineTripController.deleteStageOfStageVBOX(fxmlPlaneStage.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tripResume.removeStage(planeStage);
                    }
                });
            }

            @Override
            public void onClickAddHotelButton() throws IOException {
                FXMLLoader fxmlHotelStage = new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml"));
                TripStage hotelStage = new HotelStage();
                tripResume.addStage(hotelStage);
                defineTripController.addStageToStageVBOX(fxmlHotelStage.load());
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
                            defineTripController.deleteStageOfStageVBOX(fxmlHotelStage.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tripResume.removeStage(hotelStage);
                    }
                });
            }
        });
    }

    public void updateTripSteps(TripResume tripResume){
        AtomicReference<CityModel> source = new AtomicReference<>(tripResume.getSource());
        tripResume.getStages().forEach(
                s -> {
                    if(s instanceof PlaneStage){
                        s.setSource(source.get());
                        source.set(s.getDestination());
                        ((PlaneStage)s).setDistance(s.getDestination().distanceCompute(s.getSource())); //comme la liste est polymorphique, on doit faire un cast de s en planestage
                        s.durationCompute();
                        s.priceCompute();
//                        PlaneStageViewController planeStageController = s.getFxml().getController();
//                        planeStageController.updateLabels();
                    }
                }
        );
    }
}