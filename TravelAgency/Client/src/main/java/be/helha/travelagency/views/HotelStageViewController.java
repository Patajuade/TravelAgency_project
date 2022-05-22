package be.helha.travelagency.views;
import be.helha.common.models.HotelStage;
import be.helha.common.interfaces.IViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for HotelStage anchor pane
 * Implements Initializable and IViewController interfaces
 */
public class HotelStageViewController implements Initializable, IViewController {

    HotelStage hotelStage;
    Listener listener;

    @FXML
    private Label bottomInfoLabel;

    @FXML
    private Spinner<Integer> numberOfNightsSpinner;

    @FXML
    private Spinner<Integer> pricePerNightSpinner;

    @FXML
    private Label topInformationLabel;

    /**
     * Listener interface
     */
    public interface Listener {
        void onUpperNumberOfNightsSpinner();
        void onUpperPricePerNightsSpinner();
        void onCloseButtonClick();
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * Used for the spinners value management
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfNightsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        numberOfNightsSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleNumberOfNightsSpinner();
        }));
        pricePerNightSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000));
        pricePerNightSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            this.handleNumberOfNightsSpinner();
        }));
    }

    /**
     * Listener of the numberOfNights Spinner
     * this function is used by Scene Builder in the OnMouseClicked and OnKeyReleased events on the spinner
     */
    @FXML
    private void handleNumberOfNightsSpinner(){
        listener.onUpperNumberOfNightsSpinner();
    }

    /**
     * Listener of the pricePerNights Spinner
     * this function is used by Scene Builder in the OnMouseClicked and OnKeyReleased events on the spinner
     */
    @FXML
    private void handlePricePerNightsSpinner(){
        listener.onUpperPricePerNightsSpinner();
    }

    /**
     * Handles the OnClose listener
     * this function is used by Scene Builder in the OnMouseClicked event
     */
    @FXML
    private void handleClose(){
        listener.onCloseButtonClick();
    }

    /**
     * Updates both the hotel stage labels and spinners text
     */
    @Override
    public void update() {
        updateLabels();
        updateComponent();
    }

    /**
     * Updates both spinners text with new values
     */
    private void updateComponent() {
        numberOfNightsSpinner.getEditor().setText(String.valueOf(hotelStage.getNumberOfNights()));
        pricePerNightSpinner.getEditor().setText(String.valueOf(hotelStage.getPricePerNight()));
    }

    /**
     * Sets the text of a label
     * @param label a label of the HotelStage anchor pane
     */
    private void updateLabel(Label label){
        label.setText(hotelStage.getNumberOfNights() + " nuit(s) à l'hôtel pour " + hotelStage.getPrice() + " euros" );
    }

    /**
     * Updates the top and bottom labels of HotelStage anchor pane using the UpdateLabel() method formatting
     */
    public void updateLabels(){
        updateLabel(bottomInfoLabel);
        updateLabel(topInformationLabel);
    }

    public int getNumberOfNights(){
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(numberOfNightsSpinner.getEditor().getText())));
        }catch(NumberFormatException e) {
            return 0;
        }
    }
    public int getPricePerNights(){
        try {
            return Math.min(1000, Math.max(0, Integer.parseInt(pricePerNightSpinner.getEditor().getText())));
        }catch(NumberFormatException e) {
            return 0;
        }
    }

    public void setHotelStage(HotelStage hotelStage) {
        this.hotelStage = hotelStage;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
