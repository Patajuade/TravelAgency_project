package com.example.travelagency.controllers;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import com.example.travelagency.views.ChooseDestinationViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class travelAgencyApplication extends Application  {
    ChooseDestinationViewController controller;
    CityController cityController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(travelAgencyApplication.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        ChooseButtonManagement();
        cityController = new CityController();
        cityController.init();
        controller.showCities(cityController.getCitiesList());
        searchCityByNameManagement();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
        stage.show();
        stage.show();
        stage.show();
        stage.show();
    }

    private void searchCityByNameManagement() {
        controller.setTextFieldListener(new ChooseDestinationViewController.TextFieldListener() {
            @Override
            public void onKeyReleased() {
                controller.showCities(cityController.searchCityByName(controller.getText()));
            }
        });
    }

    private void ChooseButtonManagement() {
        controller.setChooseButtonListener(new ChooseDestinationViewController.ChooseButtonListener() {
            @Override
            public void onChooseDestinationButtonClick() {
                //Donc on affiche la ville entière dans le text field
                controller.setText(controller.getText());
                //Et on la choisit dans le modèle ->
                cityController.chooseCity(controller.getText());
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}