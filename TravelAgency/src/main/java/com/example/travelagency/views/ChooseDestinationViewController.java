package com.example.travelagency.views;
import com.example.travelagency.models.ManagementCity;
import com.example.travelagency.models.CityModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

/**
 * View controller for ChooseDestination window
 */
public class ChooseDestinationViewController    {

    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;
    private ArrayList<CityModel> cities;
    private DefineTripViewController defineTripController;
    private Listener listener;
    private ManagementCity cityController;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Listener interface
     */
    public interface Listener {
        void selectedDestination();
    }

    /**
     * Shows all the ciies in the list in alphabetical order
     */
    public void showCities(){
        cityController.getCitiesList().sort((o1,o2)->{return o1.getCityName().compareTo(o2.getCityName());});
        this.cities = cityController.searchCityByName(getText());
        ChooseDestinationListView.getItems().setAll(this.cities);
    }

    /**
     * Listener of the "choisir..." button
     * this function is used by Scene Builder in the OnAction button event
     */
    @FXML
    void selectedDestinationButton() {
        listener.selectedDestination();
    }

    /**
     * handle the selectedDestination listener
     * @param event waits for 2 clicks on an element of the list
     */
    @FXML
    void selectedDestinationListView(MouseEvent event) {
        if(event.getClickCount()==2){
            listener.selectedDestination();
        }
    }

    /**
     * Seaches for a matching city when keyboard key is released
     * this function is used in the OnKeyReleased event of the ChooseDestinationTextField textField
     */
    @FXML
    void chooseDestinationOnKeyReleased() {
        showCities();
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

    public void setCityController(ManagementCity cityController) {
        this.cityController = cityController;
    }
}