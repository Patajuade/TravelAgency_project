package com.example.travelagency.views;

import com.example.travelagency.models.HotelStage;
import com.example.travelagency.models.IViewController;
import com.example.travelagency.models.PlaneStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaneStageViewController implements Initializable, IViewController {
    @FXML
    private Label BottomInformationLabel;

    @FXML
    private Button ChooseButton;

    @FXML
    private Button CloseButton;

    @FXML
    private RadioButton RadioButton700;

    @FXML
    private RadioButton RadioButton900;

    @FXML
    private Label TopInformationLabel;

    @FXML
    private Spinner<Integer> WaitingTimeSpinner;

    @FXML
    private ComboBox<Double> pricePerKmComboBox;

    PlaneStage planeStage = new PlaneStage();
    ObservableList<Double> optionsList =
            FXCollections.observableArrayList(
                    0.025,
                    0.0507,
                    0.0758,
                    0.1005,
                    0.2
            );


    private Listener listener;

    public int getWaitingTime() {
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(WaitingTimeSpinner.getEditor().getText())));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public PlaneStage getPlaneStage() {
        return planeStage;
    }

    public void setPlaneStage(PlaneStage planeStage) {
        this.planeStage = planeStage;
        // TODO : update les labels des sous fenêtres
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void changeButtonText() {
        ChooseButton.setText(planeStage.getDestination().getCityName()+ "(" + planeStage.getDestination().getCountryName() + ")");
    }

    public void updateLabels() {
        updateTopLabel();
        updateBottomLabel();
    }

    private void updateTopLabel() {
        if(planeStage.getDestination() != null && planeStage.getSource() != null){
            TopInformationLabel.setText("Voyage en avion (" + planeStage.getDestination().getCityName() + ","
                    + planeStage.getDistance() + "km," + planeStage.getDuration() + "heures,"
                    + planeStage.getPrice() +"euros)");
        }
        else{
            TopInformationLabel.setText("Voyage en avion ");
        }
    }

    public void updateBottomLabel() {
        if(planeStage.getDestination() != null && planeStage.getSource() != null) {
            BottomInformationLabel.setText(planeStage.getDistance() + "km," + planeStage.getDuration() + "heures," + planeStage.getPrice() + "euros");
        }
        else{
            BottomInformationLabel.setText("0km, 0heures, 0euros ");
        }
    }

    public void calculateDuration(){
        planeStage.durationCompute();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WaitingTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        WaitingTimeSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleWaitingTimeSpinner();
        }));
        pricePerKmComboBox.setItems(optionsList);
    }

    @Override
    public void update() {
        updateLabels();
        updateComponents();
    }

    private void updateComponents() {
        if(planeStage.getDestination() !=null){
            changeButtonText();
            pricePerKmComboBox.setValue(planeStage.getPricePerKm());
            if(planeStage.getSpeed() == 700){
                RadioButton700.setSelected(true);
            }
            else{
                RadioButton900.setSelected(true);
            }
            WaitingTimeSpinner.getEditor().setText(String.valueOf(planeStage.getWaitingTime()));
        }
    }

    public interface Listener {
        void onChooseButtonClick() throws IOException;
        void onRadioButton700Click();
        void onRadioButton900Click();
        void onUpperWaitingTimeSpinner();
        void onCloseButtonClick();
        void onPricePerKmChange(Double value);
    }

    @FXML
    private void handleWaitingTimeSpinner(){
        listener.onUpperWaitingTimeSpinner();
    }

    @FXML
    private void handleChooseButtonClick(ActionEvent event) throws IOException {
        listener.onChooseButtonClick();
    }

    @FXML
    private void handleRadioButton700Click(ActionEvent event){
        listener.onRadioButton700Click();
    }

    @FXML
    private void handleRadioButton900Click(ActionEvent event){
        listener.onRadioButton900Click();
    }

    @FXML
    void setOnActionComboBox(ActionEvent event) {
        listener.onPricePerKmChange(pricePerKmComboBox.getValue());
    }

    @FXML
    private void handleCloseButtonClick(ActionEvent event){
        listener.onCloseButtonClick();
    }

}
