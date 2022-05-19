package com.example.travelagency.views;
import be.helha.common.models.ManagementCity;
import be.helha.common.models.CityModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class ChooseDestinationViewController    {


    private ArrayList<CityModel> cities;
    private DefineTripViewController defineTripController;

    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void selectedDestination();
    }

    private ManagementCity cityController;

    public void setCityController(ManagementCity cityController) {
        this.cityController = cityController;
    }

    private String formatCityName(CityModel city){
    return city.getCityName()+" (" + city.getCountryName()+")";
    }

    //On affiche toutes les villes de la listes dans l'ordre alphabétique
    public void showCities(){
        cityController.getCitiesList().sort((o1,o2)->{return o1.getCityName().compareTo(o2.getCityName());});
        this.cities = cityController.searchCityByName(getText());
        ChooseDestinationListView.getItems().setAll(this.cities);
    }

    public CityModel getCurrentCity(){
        int selectedIndex = ChooseDestinationListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            return this.cities.get(selectedIndex);
        }
        return null;
    }

    //On récupère le contenu du textField
    public String getText(){
        return ChooseDestinationTextField.getText();
    }

    //Gestion du bouton Choisir... MVC
    @FXML
    void selectedDestinationButton(ActionEvent event) {
        listener.selectedDestination();
    }
    @FXML
    void selectedDestinationListView(MouseEvent event) {
        if(event.getClickCount()==2){
            listener.selectedDestination();
        }
    }

    //Gestion de la recherche automatique quand on lache une touche du clavier
    @FXML
    void chooseDestinationOnKeyReleased(KeyEvent event) {
        //this.cities = cityController.searchCityByName(getText());
        showCities();
        //showCities(cityController.searchCityByName(getText()));
    }
}