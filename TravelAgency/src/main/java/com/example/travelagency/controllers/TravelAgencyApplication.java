package com.example.travelagency.controllers;
import com.example.travelagency.models.*;
import com.example.travelagency.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TravelAgencyApplication extends Application implements TripsResumeViewController.Listener{

    TripsResume trips = new TripsResume();
    TripsResumeViewController tripsResumeViewController;

    @Override
    public void start(Stage stage)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("TripsResume.fxml"));
        Scene TripsResumeScene = new Scene(fxmlLoader.load());
        tripsResumeViewController = fxmlLoader.getController();
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
        TripResume tripResume = new TripResume(new FXMLLoader(TravelAgencyApplication.class.getResource("TripResume.fxml")));
        trips.addTripResume(tripResume);
        tripResume.setAnchorPane();
        tripsResumeViewController.addTripResumeToTripVbox(tripResume);
        TripResumeViewController tripResumeViewController = tripResume.getFxml().getController();
        tripResumeViewController.setTripResume(tripResume);
        tripResumeViewController.setListener(new TripResumeViewController.Listener() {
            @Override
            public void onClickDeleteTripButton() {
                try {
                    tripsResumeViewController.removeTripResumeToTripVbox(tripResume);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trips.removeTripResume(tripResume);
            }

            @Override
            public void onClickShowTripButton() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
                Scene DefineTripScene = new Scene(fxmlLoader.load());
                DefineTripViewController defineTripController = fxmlLoader.getController();
                defineTripController.setListener(new DefineTripViewController.Listener() {
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
                                //TODO : Remplacer la variable par le voyage.setCitySource
                                tripResume.setSource(chooseDestinationViewController.getCurrentCity());
                                defineTripController.changeStartCity(tripResume.getSource());
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
                        PlaneStage planeStage = new PlaneStage(new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml")));
                        planeStage.setSource(tripResume.getSource());
                        tripResume.addStage(planeStage);
                        planeStage.setAnchorPane();
                        defineTripController.addStageToStageVBOX(planeStage);
                        PlaneStageViewController planeStageController = planeStage.getFxml().getController();
                        planeStageController.setPlaneStage(planeStage);
                        planeStageController.setListener(new PlaneStageViewController.Listener() {
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
                                        planeStage.setDistance(planeStage.getDestination().distanceCompute(tripResume.getSource()));
                                        planeStage.durationCompute();
                                        planeStage.priceCompute();
                                        planeStageController.changeButtonText();
                                        planeStageController.updateLabels();
                                    }
                                });
                                chooseDestinationViewController.setCityController(CityController.getInstance());
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
                                try {
                                    defineTripController.deleteStageOfStageVBOX(planeStage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                tripResume.removeStage(planeStage);
                            }
                        });
                    }

                    @Override
                    public void onClickAddHotelButton() throws IOException {
                        TripStage hotelStage = new HotelStage(new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml")));
                        tripResume.addStage(hotelStage);
                        hotelStage.setAnchorPane();
                        defineTripController.addStageToStageVBOX(hotelStage);
                        HotelStageViewController hotelStageController = hotelStage.getFxml().getController();
                        hotelStageController.setListener(new HotelStageViewController.Listener() {
                            @Override
                            public void onUpperNumberOfNightsSpinner() {
                                int numberOfNights = hotelStageController.getNumberOfNightsSpinner().getValue();
                                hotelStageController.getHotelStage().setNumberOfNights(numberOfNights);
                                hotelStageController.calculatePricePerNight();
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
                                hotelStageController.updatePrice();}

                            @Override
                            public void onCloseButtonClick() {
                                try {
                                    defineTripController.deleteStageOfStageVBOX(hotelStage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                tripResume.removeStage(hotelStage);
                            }
                        });
                    }
                });
                Stage stage = new Stage();
                stage.setTitle("Définir son voyage");
                stage.setScene(DefineTripScene);
                stage.show();
            }
        });
    }
}