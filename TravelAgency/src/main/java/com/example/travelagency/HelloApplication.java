package com.example.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        //launch();
        File destinationFile = new File("mapinfo.txt");
        //readDestinationFile(destinationFile);
        CityInfos cityInfos = new CityInfos("pouet","pouet","pouet","pouet");
        cityInfos.parseMapInfoFile();
    }

    private static void readDestinationFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
    }
}