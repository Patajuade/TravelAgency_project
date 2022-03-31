package com.example.travelagency.controllers;

import com.example.travelagency.views.ChooseDestinationViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class travelAgencyApplication extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(travelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ChooseDestinationViewController controller = fxmlLoader.getController();
        controller.setListener(new ChooseDestinationViewController.Listener() {
            @Override
            public void onChooseDestinationButtonClick() {
                //Donc on affiche la ville entière dans le text field
                controller.setText("A remplir");
                //Et on la choisit dans le modèle ->

            }
        });
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}