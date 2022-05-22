package be.helha.travelagency.controllers;
import be.helha.common.messages.AddTripMessage;
import be.helha.common.messages.SaveTripsMessage;
import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import be.helha.common.networks.ObjectSocket;
import be.helha.travelagency.networks.NetworkCommunicationThread;
import be.helha.travelagency.views.ErrorServerViewController;
import be.helha.travelagency.views.TripResumeViewController;
import be.helha.travelagency.views.TripsResumeViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class Client extends Application implements TripsResumeViewController.Listener {

    TripsResumeViewController tripsResumeViewController;
    TripsResume tripsResume = new TripsResume();
    TripResume editingTripResume;
    Stage currentStage;
    private ObjectSocket objectSocket;
    public static final int port = 1099;
    boolean isConnected;

    @Override
    public void start(Stage stage) throws IOException {
        onTryToConnect();
        if(isConnected == true){
            tripsResumeViewController = showFMXL(stage,TripsResumeViewController.class.getResource("TripsResume.fxml"));
            tripsResumeViewController.setListener(this);
            stage.setTitle("Voyages");
            currentStage = stage;
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void onTryToConnect(){
        try {
            this.objectSocket = new ObjectSocket(new Socket("localhost", port));
            isConnected = true;
            NetworkCommunicationThread listeningThread = new NetworkCommunicationThread(objectSocket, new NetworkCommunicationThread.Listener() {
                @Override
                public void getTrips(ArrayList<TripResume> trips) {
                    Platform.runLater(()-> {
                        try {
                            getTripsFromServer(trips);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                @Override
                public void setList(ArrayList<TripResume> trips) {
                    Platform.runLater(()->{
                        Client.this.tripsResume.setTrips(trips);
                        try {
                            updateTrips();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

            });
            listeningThread.start();
        } catch (IOException e) {
            //TODO : Faire un truc fxml stylé et obliger à relancer tout
            try {
                isConnected = false;
                currentStage = new Stage();
                showFMXL(currentStage,ErrorServerViewController.class.getResource("ErrorServer.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onClickCreateTripButton() throws IOException {
        currentStage.close();
        TripResume tripResume = new TripResume();
        tripsResume.addTripResume(tripResume);
        DefineTripController defineTripController = new DefineTripController();
        defineTripController.setTripResume(tripResume);
        defineTripController.show();
        managementTripResumeFxml(tripResume, defineTripController);
        AddTripOnServ();
    }

    private void AddTripOnServ() throws IOException {
        objectSocket.write(new AddTripMessage(tripsResume.getTrips()));
        updateTrips();
    }

    private void managementTripResumeFxml(TripResume tripResume, DefineTripController defineTripController) throws IOException {
        FXMLLoader fxmlTripResume =  new FXMLLoader(TripResumeViewController.class.getResource("TripResume.fxml"));
        AnchorPane anchorPane = fxmlTripResume.load();
        TripResumeViewController tripResumeViewController = fxmlTripResume.getController();
        tripsResumeViewController.addTripResumeToTripVbox(anchorPane);
        tripResumeViewController.setTripResume(tripResume);
        tripResumeViewController.UpdateDatas();
        defineTripController.setListener(new DefineTripController.Listener() {
            @Override
            public void isClosed() throws IOException {
                currentStage.show();
                editingTripResume = defineTripController.getTripResume();
                editingTripResume.calculateAll();
                editingTripResume.setDate(defineTripController.getDate());
                editingTripResume.setName(defineTripController.getNameTrip());
                tripResumeViewController.UpdateDatas();
                SaveTripsOnServ();
            }
        });
        tripResumeViewController.setListener(new TripResumeViewController.Listener() {
            @Override
            public void onClickShowTripButton() throws IOException {
                currentStage.close();
                defineTripController.show();
            }
            @Override
            public void onClickDeleteTripButton() throws IOException {
                tripsResumeViewController.removeTripResumeToTripVbox(anchorPane);
                tripsResume.removeTripResume(tripResume);
                SaveTripsOnServ();
            }
        });
    }

    private void SaveTripsOnServ() throws IOException {
        objectSocket.write(new SaveTripsMessage(tripsResume.getTrips()));
        updateTrips();
    }

    public void getTripsFromServer(ArrayList<TripResume> tripsList) throws IOException {
        tripsResume.setTrips(tripsList);
    }

    public void updateTrips() throws IOException {
        tripsResumeViewController.cleanVbox();
        for (TripResume tripResume : tripsResume.getTrips()) {
            DefineTripController defineTripController = new DefineTripController();
            defineTripController.setTripResume(tripResume);
            managementTripResumeFxml(tripResume, defineTripController);
        }
    }

    private <T> T showFMXL(Stage stage, URL url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load());
        T controller = fxmlLoader.getController();
        stage.setMinWidth(650);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
        return controller;
    }
}