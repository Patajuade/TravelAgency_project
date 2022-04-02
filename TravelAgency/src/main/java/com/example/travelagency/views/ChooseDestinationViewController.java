package com.example.travelagency.views;

import com.example.travelagency.CityController;
import com.example.travelagency.CityModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseDestinationViewController    {
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
    public interface ChooseDestinationListener {
        CityModel selectedDestination();
    }
    private CityController cityController;
    public void setCityController(CityController cityController) {
        this.cityController = cityController;
    }
    private List<CityModel> citiesModelTemp;
    private CityModel currentCity;
    public CityModel getCurrentCity(){
        return this.citiesModelTemp.get(ChooseDestinationListView.getSelectionModel().getSelectedIndex());
    }
    private ChooseDestinationListener chooseDestinationListener;

    public void setChooseDestinationListener(ChooseDestinationListener listener) {
        this.chooseDestinationListener = listener;
    }

    //récupérer la liste pour pouvoir l'afficher

    //Gestion de la recherche automatique quand on lache une touche du clavier
    @FXML
    void chooseDestinationOnKeyReleased(KeyEvent event) {
        this.citiesModelTemp = cityController.searchCityByName(getText());
        showCities(cityController.searchCityByName(getText()));
    }

    //On récupère le contenu du textField
    public String getText(){
        return ChooseDestinationTextField.getText();
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
}