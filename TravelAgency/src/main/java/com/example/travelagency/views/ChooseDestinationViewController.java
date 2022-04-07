package com.example.travelagency.views;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import com.example.travelagency.interfaces.ChooseDestinationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ChooseDestinationViewController    {

    private ChooseDestinationListener chooseDestinationListener;
    private CityController cityController;
    private List<CityModel> citiesModelTemp;

    public CityModel getCurrentCity(){
        return this.citiesModelTemp.get(ChooseDestinationListView.getSelectionModel().getSelectedIndex());
    }

    //On récupère le contenu du textField
    public String getText(){
        return ChooseDestinationTextField.getText();
    }

    public void setChooseDestinationListener(ChooseDestinationListener listener) {
        this.chooseDestinationListener = listener;
    }

    public void setCityController(CityController cityController) {
        this.cityController = cityController;
    }


    //On affiche toutes les villes de la listes dans l'ordre alphabétique
    private void showCities(ArrayList<CityModel> list){
        this.citiesModelTemp = list;
        //Modif pour pouvoir afficher dans l'ordre alphabétique
        List<String> stringCityList = new ArrayList<>();
        ChooseDestinationListView.getItems().clear();
        for (CityModel c:list) {
            stringCityList.add(c.getCityName()+" (" + c.getCountryName()+")");
        }
        Collections.sort(stringCityList);
        for (String s:stringCityList) {
            ChooseDestinationListView.getItems().add(s);
        }
    }

    public void showAllCities(){
        this.showCities(cityController.getCitiesList());
    }

    public void chooseButtonManagement(Stage stage) {
        setChooseDestinationListener(new ChooseDestinationListener() {
            @Override
            public CityModel selectedDestination() {
                stage.close();
                return getCurrentCity();
            }
        });
    }

    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;
    //Gestion du bouton Choisir... MVC
    @FXML
    void selectedDestinationButton(ActionEvent event) {
        chooseDestinationListener.selectedDestination();
    }
    @FXML
    void selectedDestinationListView(MouseEvent event) {
        if(event.getClickCount()==2){
            chooseDestinationListener.selectedDestination();
        }
    }
    //Gestion de la recherche automatique quand on lache une touche du clavier
    @FXML
    void chooseDestinationOnKeyReleased(KeyEvent event) {
        this.citiesModelTemp = cityController.searchCityByName(getText());
        showCities(cityController.searchCityByName(getText()));
    }
}