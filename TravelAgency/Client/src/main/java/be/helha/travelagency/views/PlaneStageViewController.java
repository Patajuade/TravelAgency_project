package be.helha.travelagency.views;

import be.helha.common.interfaces.IViewController;
import be.helha.common.models.PlaneStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for PlaneStage anchor pane
 * Implements Initializable and IViewController interfaces
 */
public class PlaneStageViewController implements Initializable, IViewController {
    @FXML
    private Label BottomInformationLabel;

    @FXML
    private Button ChooseButton;

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

    PlaneStage planeStage;
    ObservableList<Double> optionsList =
            FXCollections.observableArrayList(
                    0.025,
                    0.0507,
                    0.0758,
                    0.1005,
                    0.2
            );


    private Listener listener;

    /**
     * Changes the test of the ChooseButton to the selected city
     */
    public void changeButtonText() {
        ChooseButton.setText(planeStage.getDestination().getCityName()+ "(" + planeStage.getDestination().getCountryName() + ")");
    }

    /**
     * Updates the text of both top and bottom labels
     */
    public void updateLabels() {
        updateTopLabel();
        updateBottomLabel();
    }

    /**
     * Updates the text of the top label
     */
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

    /**
     * Updates the text of the bottom label
     */
    public void updateBottomLabel() {
        if(planeStage.getDestination() != null && planeStage.getSource() != null) {
            BottomInformationLabel.setText(planeStage.getDistance() + "km," + planeStage.getDuration() + "heures," + planeStage.getPrice() + "euros");
        }
        else{
            BottomInformationLabel.setText("0km, 0heures, 0euros ");
        }
    }

    /**
     * Updates both the plane stage labels, comboBoxes, spinners and radioButtons
     */
    @Override
    public void update() {
        updateLabels();
        updateComponents();
    }

    /**
     * Updates both the plane stage choose button text, comboBoxes, spinners and radioButtons values
     */
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

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * Used for the spinners value management
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WaitingTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        WaitingTimeSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleWaitingTimeSpinner();
        }));
        pricePerKmComboBox.setItems(optionsList);
    }

    /**
     * Listener interface
     * Contains action functions
     */
    public interface Listener {
        /**
         * @throws IOException
         */
        void onChooseButtonClick() throws IOException;

        /**
         * Action when RadioButton700 is clicked
         */
        void onRadioButton700Click();

        /**
         * Action when RadioButton900 is clicked
         */
        void onRadioButton900Click();

        /**
         * Action when WaitingTimeSpinner is clicked or written in
         */
        void onUpperWaitingTimeSpinner();

        /**
         * Action when Close is clicked
         */
        void onCloseButtonClick();

        /**
         * Action when pricePerKmComboBox is clicked
         * @param value of price per km
         */
        void onPricePerKmChange(Double value);
    }

    /**
     * Listener of the waitingTime spinner
     * this function is used by Scene Builder in the OnMouseClicked and OnKeyReleased events on the spinner
     */
    @FXML
    private void handleWaitingTimeSpinner(){
        listener.onUpperWaitingTimeSpinner();
    }

    /**
     * Listener of the Choose button click
     * @throws IOException management of input/output exceptions.
     */
    @FXML
    private void handleChooseButtonClick() throws IOException {
        listener.onChooseButtonClick();
    }

    /**
     * Listener of the RadioButton700 click
     * this function is used by Scene Builder in the OnAction event
     */
    @FXML
    private void handleRadioButton700Click(){
        listener.onRadioButton700Click();
    }

    /**
     * Listener of the RadioButton900 click
     * this function is used by Scene Builder in the OnAction event
     */
    @FXML
    private void handleRadioButton900Click(){
        listener.onRadioButton900Click();
    }

    /**
     * Listener of the CloseButton click
     * this function is used by Scene Builder in the OnAction event
     */
    @FXML
    private void handleCloseButtonClick(){
        listener.onCloseButtonClick();
    }

    /**
     * Listener of the comboBox click
     * this function is used by Scene Builder in the OnAction event
     */
    @FXML
    void setOnActionComboBox() {
        listener.onPricePerKmChange(pricePerKmComboBox.getValue());
    }

    public int getWaitingTime() {
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(WaitingTimeSpinner.getEditor().getText())));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setPlaneStage(PlaneStage planeStage) {
        this.planeStage = planeStage;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
