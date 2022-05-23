package be.helha.travelagency.controllers;

import be.helha.common.interfaces.IViewController;
import be.helha.common.models.*;
import be.helha.travelagency.views.ChooseDestinationViewController;
import be.helha.travelagency.views.DefineTripViewController;
import be.helha.travelagency.views.HotelStageViewController;
import be.helha.travelagency.views.PlaneStageViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller of DefineTrip
 * Implements DefineTripViewController.Listener
 */
public class DefineTripController implements DefineTripViewController.Listener {
    DefineTripViewController defineTripViewController;
    List<IViewController> listViewController = new ArrayList<>();
    TripResume tripResume;
    FXMLLoader fxmlLoader;
    private Listener listener;
    Stage stage;

    public interface Listener{
        /** Action when close button of the window is clicked
         * @throws IOException management of input/output exceptions.
         */
        void isClosed() throws IOException;
    }

    public TripResume getTripResume() {
        return tripResume;
    }

    public String getNameTrip() {
        return  defineTripViewController.getNameTrip();
    }

    public LocalDate getDate() {
        return defineTripViewController.getDate();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setTripResume(TripResume tripResume) throws IOException {
        this.tripResume = tripResume;
    }

    /**
     * Updates the view of listViewController
     */
    public void updateAllStep(){
        for (IViewController iViewController : listViewController) {
            iViewController.update();
        }
    }

    /**
     * Shows definetrip window
     * @throws IOException management of input/output exceptions.
     */
    public void show() throws IOException {
        createDefineTripFxml();
        updateViewDatas();
        updateStages();
    }

    /**
     * Updates total labels of tripResume window
     * Updates data of DefineTrip window (name, date and starting city of the trip)
     */
    private void updateViewDatas() {
        defineTripViewController.setTripResume(tripResume);
        updateTotalLabel();
        defineTripViewController.updateDatas();
    }

    /**
     * Calculates the total price, duration and distance of TripResume
     * Updates defineTripViewController total labels ( Distance, time, price)
     */
    private void updateTotalLabel() {
        tripResume.calculateAll();
        defineTripViewController.updateLabel(tripResume.getTotalDistance(),tripResume.getTotalTime(),tripResume.getTotalPrice());
    }

    /**
     * Updates HotelStage or PlaneStage display in the DefineTrip window
     * Updates the source city of a plane stage and its data (price and duration)
     * Updates Define Trip total labels values
     * @throws IOException management of input/output exceptions.
     */
    private void updateStages() throws IOException {
        tripResume.updateTripStep();
        for(TripStage tripStage : tripResume.getStages()){
            if(tripStage instanceof PlaneStage){
                managementPlaneStageFxml((PlaneStage)tripStage);
            }
            else if(tripStage instanceof HotelStage){
                managementHotelStageFxml((HotelStage) tripStage);
            }
        }
        updateTotalLabel();
    }

    /**
     * Creates the DefineTrip window
     * @throws IOException management of input/output exceptions.
     */
    private void createDefineTripFxml() throws IOException {
        managementDefineTripFxml();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            /**
             * handles the close button of the whole window
             * @param windowEvent event related to the whole window
             */
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    listener.isClosed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Opens a ChooseDestination window when ChooseButton is clicked
     * Overrides onClickChooseDestinationButton from DefineTripViewController class
     * @throws IOException management of input/output exceptions.
     */
    @Override
    public void onClickChooseDestinationButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChooseDestinationViewController.class.getResource("ChooseDestination.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
        chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
            /**
             * Overrides selectedDestination from ChooseDestinationViewController class
             * When a destination is selected, close the window and updates DefineTrip window data with new information
             */
            @Override
            public void selectedDestination() {
                stage.close();
                tripResume.setSource(chooseDestinationViewController.getCurrentCity());
                defineTripViewController.changeStartCity(chooseDestinationViewController.getCurrentCity());
                tripResume.updateTripStep();
                tripResume.calculateAll();
                updateTotalLabel();
                updateAllStep();
            }
        });
        chooseDestinationViewController.setCityController(ManagementCity.getInstance());
        chooseDestinationViewController.showCities();
        stage.setTitle("Choisir une destination");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Overrides onClickAddPlaneButton in PlaneStageViewController
     * When AddPlaneButton is clicked, a new planeStage is created
     * @throws IOException management of input/output exceptions.
     */
    @Override
    public void onClickAddPlaneButton() throws IOException {
        PlaneStage planeStage = new PlaneStage();
        tripResume.addStage(planeStage);
        managementPlaneStageFxml(planeStage);
    }

    /**
     * Creates a new DefineTrip window
     * @throws IOException management of input/output exceptions.
     */
    private void managementDefineTripFxml() throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(DefineTripViewController.class.getResource("DefineTrip.fxml"));
        Scene DefineTripScene = new Scene(fxmlLoader.load());
        stage.setMinHeight(800);
        stage.setMinWidth(650);
        defineTripViewController = fxmlLoader.getController();
        defineTripViewController.setListener(this);
        stage.setTitle("Définir son voyage");
        stage.setScene(DefineTripScene);
        stage.show();
    }

    /**
     * Creates a new PlaneStage anchor pane in the DefineTrip window
     * Updates its components and labels
     * @param planeStage is the planeStage instance created
     * @throws IOException management of input/output exceptions.
     */
    private void managementPlaneStageFxml(PlaneStage planeStage) throws IOException {
        FXMLLoader fxmlPlaneStage = new FXMLLoader(PlaneStageViewController.class.getResource("PlaneStage.fxml"));
        AnchorPane anchorPane = fxmlPlaneStage.load();
        defineTripViewController.addStageToStageVBOX(anchorPane);
        PlaneStageViewController planeStageViewController = fxmlPlaneStage.getController();
        listViewController.add(planeStageViewController);
        planeStageViewController.setPlaneStage(planeStage);
        planeStageViewController.update();
        planeStageViewController.setListener(new PlaneStageViewController.Listener() {
            /**
             * Overrides onChooseButtonClick in PlaneStageViewController
             * Opens a chooseDestination window
             * @throws IOException management of input/output exceptions.
             */
            @Override
            public void onChooseButtonClick() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(ChooseDestinationViewController.class.getResource("ChooseDestination.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                Stage stage = new Stage();
                ChooseDestinationViewController chooseDestinationViewController = fxmlLoader.getController();
                chooseDestinationViewController.setListener( new ChooseDestinationViewController.Listener() {
                    /**
                     * Overrides selectedDestination from ChooseDestinationViewController class
                     * When a destination is selected, close the window and updates planeStage anchorPane data with new information
                     */
                    @Override
                    public void selectedDestination() {
                        stage.close();
                        planeStage.setDestination(chooseDestinationViewController.getCurrentCity());
                        planeStageViewController.changeButtonText();
                        tripResume.updateTripStep();
                        planeStageViewController.updateLabels();
                        updateTotalLabel();
                        updateAllStep();
                    }
                });
                chooseDestinationViewController.setCityController(ManagementCity.getInstance());
                chooseDestinationViewController.showCities();
                stage.setTitle("Choisir une destination");
                stage.setScene(scene);
                stage.show();
            }

            /**
             * Overrides onRadioButton700Click in PlaneStageViewController
             * Sets the flying speed at 700, calculates duration, and updates DefineTrip and planeStage labels
             */
            @Override
            public void onRadioButton700Click() {
                planeStage.setFlyingSpeed(700);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }

            /**
             * Overrides onRadioButton900Click in PlaneStageViewController
             * Sets the flying speed at 900, calculates duration, and updates DefineTrip and planeStage labels
             */
            @Override
            public void onRadioButton900Click() {
                planeStage.setFlyingSpeed(900);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }

            /**
             * Overrides onUpperWaitingTimeSpinner in PlaneStageViewController
             * Sets spinner value, calculates duration and updates planeStage and DefineTrip labels
             */
            @Override
            public void onUpperWaitingTimeSpinner() {
                int waitingTime = planeStageViewController.getWaitingTime();
                planeStage.setWaitingTime(waitingTime);
                planeStage.durationCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }

            /**
             * Overrides onCloseButtonClick in PlaneStageViewController
             * Closes selected planeStage anchor pane and removes it from the tripResume list, and updates the steps and labels of tripResume, DefineTrip and planeStage windows
             */
            @Override
            public void onCloseButtonClick() {
                try {
                    defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                    tripResume.removeStage(planeStage);
                    tripResume.updateTripStep();
                    planeStageViewController.updateLabels();
                    updateTotalLabel();
                    updateAllStep();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                planeStageViewController.updateLabels();
            }

            /**
             * Overrides onPricePerKmChange in PlaneStageViewController
             * When a value is selected in the comboBox, sets value, calculate price and updates planestage and DefineTrip labels
             * @param value of price per km
             */
            @Override
            public void onPricePerKmChange(Double value) {
                planeStage.setPricePerKm(value);
                planeStage.priceCompute();
                planeStageViewController.updateLabels();
                updateTotalLabel();
            }
        });
    }

    /**
     * Overrides onClickAddHotelButton in HotelStageViewController
     * When AddHotelStageButton is clicked, a new hotelStage is created
     * @throws IOException management of input/output exceptions.
     */
    @Override
    public void onClickAddHotelButton() throws IOException {
        {
            HotelStage hotelStage = new HotelStage();
            tripResume.addStage(hotelStage);
            managementHotelStageFxml(hotelStage);
        }
    }

    /**
     * Creates a new Hotel anchor pane in the DefineTrip window
     * @param hotelStage is the hotelStage instance created
     * @throws IOException management of input/output exceptions.
     */
    private void managementHotelStageFxml(HotelStage hotelStage) throws IOException {
        FXMLLoader fxmlHotelStage = new FXMLLoader(HotelStageViewController.class.getResource("HotelStage.fxml"));
        AnchorPane anchorPane = fxmlHotelStage.load();
        defineTripViewController.addStageToStageVBOX(anchorPane);
        HotelStageViewController hotelStageViewController = fxmlHotelStage.getController();
        hotelStageViewController.setHotelStage(hotelStage);
        listViewController.add(hotelStageViewController);
        hotelStageViewController.update();
        hotelStageViewController.setListener(new HotelStageViewController.Listener() {
            /**
             * Overrides onUpperNumberOfNightsSpinner in HotelStageViewController
             * Sets spinner value, calculates duration and price and updates DefineTrip and hotelStage labels
             */
            @Override
            public void onUpperNumberOfNightsSpinner() {
                int numberOfNights = hotelStageViewController.getNumberOfNights();
                hotelStage.setNumberOfNights(numberOfNights);
                hotelStage.durationCompute();
                hotelStage.priceCompute();
                hotelStageViewController.updateLabels();
                updateTotalLabel();
            }

            /**
             * Overrides onUpperPricePerNightsSpinner in HotelStageViewController
             * Sets spinner value, calculates price per night and updates DefineTrip and hotelStage labels
             */
            @Override
            public void onUpperPricePerNightsSpinner() {
                int pricePerNight = hotelStageViewController.getPricePerNights();
                hotelStage.setPricePerNight(pricePerNight);
                hotelStage.priceCompute();
                hotelStageViewController.updateLabels();
                updateTotalLabel();
            }

            /**
             * Overrides onCloseButtonClick in HotelStageViewController
             * Closes selected hotelStage anchor pane and removes it from the tripResume list, and updates DefineTrip and hotelStage labels
             */
            @Override
            public void onCloseButtonClick() {
                try {
                    defineTripViewController.deleteStageOfStageVBOX(anchorPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tripResume.removeStage(hotelStage);
                updateTotalLabel();
            }
        });
    }
}
