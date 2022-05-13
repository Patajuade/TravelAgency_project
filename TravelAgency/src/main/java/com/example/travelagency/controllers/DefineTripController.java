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
import java.util.ArrayList;
import java.util.List;

public class DefineTripController implements DefineTripViewController.Listener{
    DefineTripViewController defineTripViewController;
    List<IViewController> listViewController = new ArrayList<>();

    public void setTripResume(TripResume tripResume) throws IOException {
        this.tripResume = tripResume;
    }

    public void updateAllStep(){
        for (IViewController iViewController : listViewController) {
            iViewController.update();
        }
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
        updateViewDatas();
        updateStages();
    }

    private void updateViewDatas() {
        defineTripViewController.setTripResume(tripResume);
        updateTotalLabel();
        defineTripViewController.updateDatas();
    }

    private void updateTotalLabel() {
        tripResume.calculateAll();
        defineTripViewController.updateLabel(tripResume.getTotalDistance(),tripResume.getTotalTime(),tripResume.getTotalPrice());
    }

    private void updateStages() throws IOException {
        tripResume.updateTripStep();
        for(TripStage tripStage : tripResume.getStages()){
            if(tripStage instanceof PlaneStage){
                managementPlaneStageFxml((PlaneStage)tripStage);
            }
            else if(tripStage instanceof HotelStage){
                managementHotelStageFxml(tripStage);
            }
        }
        updateTotalLabel();
    }

    private void createDefineTripFxml() throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(TravelAgencyApplication.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        stage.setMinHeight(800);
        stage.setMinWidth(650);
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
                tripResume.setSource(chooseDestinationViewController.getCurrentCity());
                defineTripViewController.changeStartCity(chooseDestinationViewController.getCurrentCity());
                tripResume.updateTripStep();
                tripResume.calculateAll();
                updateTotalLabel();
                updateAllStep();
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
        tripResume.addStage(planeStage);
        managementPlaneStageFxml(planeStage);
    }

    private void managementPlaneStageFxml(PlaneStage planeStage) throws IOException {
        FXMLLoader fxmlPlaneStage = new FXMLLoader(TravelAgencyApplication.class.getResource("PlaneStage.fxml"));
        AnchorPane anchorPane = fxmlPlaneStage.load();
        defineTripViewController.addStageToStageVBOX(anchorPane);
        PlaneStageViewController planeStageViewController = fxmlPlaneStage.getController();
        listViewController.add(planeStageViewController);
        planeStageViewController.setPlaneStage(planeStage);
        planeStageViewController.update();
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
                        tripResume.updateTripStep();
                        planeStageViewController.updateLabels();
                        updateTotalLabel();
                        updateAllStep();
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
                updateTotalLabel();
            }

            @Override
            public void onRadioButton900Click() {
                planeStage.setFlyingSpeed(900);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }

            @Override
            public void onUpperWaitingTimeSpinner() {
                int waitingTime = planeStageViewController.getWaitingTime();
                planeStageViewController.getPlaneStage().setWaitingTime(waitingTime);
                planeStageViewController.calculateDuration();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }

            @Override
            public void onCloseButtonClick() {
                try {
                    defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                    tripResume.removeStage(planeStage);
                    tripResume.updateTripStep();
                    planeStageViewController.updateLabels();
                    updateTotalLabel();
                    updateAllStep();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                planeStageViewController.updateLabels();
            }

            @Override
            public void onPricePerKmChange(Double value) {
                planeStage.setPricePerKm(value);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }
        });
    }

    @Override
    public void onClickAddHotelButton() throws IOException {
        {
            TripStage hotelStage = new HotelStage();
            tripResume.addStage(hotelStage);
            managementHotelStageFxml(hotelStage);
        }
    }

    private void managementHotelStageFxml(TripStage hotelStage) throws IOException {
        FXMLLoader fxmlHotelStage = new FXMLLoader(TravelAgencyApplication.class.getResource("HotelStage.fxml"));
        AnchorPane anchorPane = fxmlHotelStage.load();
        defineTripViewController.addStageToStageVBOX(anchorPane);
        HotelStageViewController hotelStageViewController = fxmlHotelStage.getController();
        listViewController.add(hotelStageViewController);
        hotelStageViewController.update();
        hotelStageViewController.setListener(new HotelStageViewController.Listener() {
            @Override
            public void onUpperNumberOfNightsSpinner() {
                int numberOfNights = hotelStageViewController.getNumberOfNights();
                hotelStageViewController.getHotelStage().setNumberOfNights(numberOfNights);
                hotelStageViewController.calculatePricePerNight();
                hotelStageViewController.calculateDuration(numberOfNights);
                hotelStageViewController.updateLabels();
                updateTotalLabel();
            }

            @Override
            public void onUpperPricePerNightsSpinner() {
                int pricePerNight = hotelStageViewController.getPricePerNights();
                hotelStageViewController.getHotelStage().setPricePerNight(pricePerNight);
                hotelStageViewController.calculatePricePerNight();
                hotelStageViewController.updateLabels();
                updateTotalLabel();
            }

            @Override
            public void onCloseButtonClick() {
                try {
                    defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tripResume.removeStage(hotelStage);
                updateTotalLabel();
            }
        });
    }
}
