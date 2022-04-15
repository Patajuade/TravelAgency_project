package com.example.travelagency.views;
import com.example.travelagency.models.CityController;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.interfaces.ChooseDestinationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ChooseDestinationViewController    {

    private ChooseDestinationListener chooseDestinationListener;
    private CityController cityController;
    private ArrayList<CityModel> cities;
    private DefineTripController defineTripController;

    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;

    public CityModel getCurrentCity(){
        return this.cities.get(ChooseDestinationListView.getSelectionModel().getSelectedIndex());
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

    //Pour connaître defineTrip dans ChooseDestination, pour la communication entre les deux fenêtres
    public void setDefineTripController(DefineTripController defineTripController) {
        this.defineTripController = defineTripController;
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

    public void chooseButtonManagement(Stage stage) {
        setChooseDestinationListener(new ChooseDestinationListener() {
            @Override
            public CityModel selectedDestination() {
                stage.close();
                defineTripController.changeChooseButtonText();
                return getCurrentCity();
            }
        });
    }

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
    CityModel isCitySelected(){
        if(chooseDestinationListener.selectedDestination()==null){
            System.out.println("pouet");
        }
        return chooseDestinationListener.selectedDestination();
    }
    //Gestion de la recherche automatique quand on lache une touche du clavier
    @FXML
    void chooseDestinationOnKeyReleased(KeyEvent event) {
        //this.cities = cityController.searchCityByName(getText());
        showCities();
        //showCities(cityController.searchCityByName(getText()));
    }
}