package com.example.travelagency;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) throws IOException {
        //launch();
        CityController cityController = new CityController();
        cityController.init();
        //cityController.chooseCity("Marseille");
        //cityController.showCity();
        cityController.showCities(cityController.searchCityByName("Mars"));

    }
}