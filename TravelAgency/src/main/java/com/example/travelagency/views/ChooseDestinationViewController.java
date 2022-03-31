package com.example.travelagency.views;

import com.example.travelagency.CityModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseDestinationViewController    {
    @FXML
    private TextField ChooseDestinationTextField;
    @FXML
    private ListView ChooseDestinationListView;
    //Gestion du bouton Choisir... MVC
    @FXML
    private Button ChooseDestinationButton;
    @FXML
    void onChooseDestinationButtonClick(ActionEvent event) {
        chooseButtonListener.onChooseDestinationButtonClick();
    }

    public interface ChooseButtonListener{
        void onChooseDestinationButtonClick();
    }
    private ChooseButtonListener chooseButtonListener;

    public void setChooseButtonListener(ChooseButtonListener listener) {
        this.chooseButtonListener = listener;
    }

    //Gestion de la recherche automatique
    @FXML
    void ChooseDestinationOnKeyReleased(KeyEvent event) {
        textFieldListener.onKeyReleased();
    }
    public interface TextFieldListener{
        void onKeyReleased();
    }

    public void setTextFieldListener(TextFieldListener textFieldListener) {
        this.textFieldListener = textFieldListener;
    }

    private TextFieldListener textFieldListener;

    public void setText(String string){
        ChooseDestinationTextField.setText(string);
    }

    public String getText(){
        return ChooseDestinationTextField.getText();
    }

    public void showCities(ArrayList<CityModel> list){
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


}