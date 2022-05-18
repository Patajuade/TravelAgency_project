package com.example.travelagency;
import com.example.travelagency.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TripResumeTest {

    private TripResume tripResume;
    private HotelStage hotelStage;
    private PlaneStage planeStage;
    private CityModel destination;
    private CityModel source;

    @BeforeEach
    public void setUp(){
        tripResume = new TripResume();
        hotelStage = new HotelStage();
        planeStage = new PlaneStage();
        destination = new CityModel("destination","10","10","destinationCountry");
        source = new CityModel("source","20","20","sourceCountry");

        hotelStage.setDuration(5);
        hotelStage.setDistance(1000);
        hotelStage.setPrice(20);
        hotelStage.setDestination(destination);
        hotelStage.setSource(source);

        planeStage.setDuration(5);
        planeStage.setDistance(1000);
        planeStage.setPrice(20);
        planeStage.setDestination(destination);
        planeStage.setSource(source);
    }

    @Test
    public void addStage(){
        tripResume.addStage(planeStage);
        tripResume.addStage(hotelStage);
        ArrayList<TripStage> stages = tripResume.getStages();
        assertEquals(stages.size(),2);
    }

    @Test
    public void removeStage(){
        tripResume.removeStage(planeStage);
        tripResume.removeStage(hotelStage);
        ArrayList<TripStage> stages = tripResume.getStages();
        assertEquals(stages.size(),0);
    }

    @Test
    public void calculateAll_ifHotelStage(){
        tripResume.addStage(hotelStage); //deux fois la même nuit à l'hôtel
        tripResume.addStage(hotelStage);
        tripResume.calculateAll();

        assertEquals(tripResume.getTotalTime(),10);
        assertEquals(tripResume.getTotalDistance(),3089.52);
        assertEquals(tripResume.getTotalPrice(),40);
    }

    @Test
    public void calculateAll_ifPlaneStage(){
        tripResume.addStage(planeStage);
        tripResume.addStage(planeStage);
        tripResume.calculateAll();

        assertEquals(tripResume.getTotalTime(),10);
        assertEquals(tripResume.getTotalDistance(),3089.52);
        assertEquals(tripResume.getTotalPrice(),40);
    }

    @Test
    public void calculateAll_ifPlaneStageAndHotelStage(){
        tripResume.addStage(planeStage);
        tripResume.addStage(hotelStage);
        tripResume.calculateAll();

        assertEquals(tripResume.getTotalTime(),10);
        assertEquals(tripResume.getTotalDistance(),3089.52);
        assertEquals(tripResume.getTotalPrice(),40);
    }

    @Test
    public void updateTripStage(){
        tripResume.setSource(source);
        tripResume.addStage(planeStage);
        PlaneStage planeStage1 = new PlaneStage();
        planeStage1.setDuration(5);
        planeStage1.setDistance(1000);
        planeStage1.setPrice(20);
        planeStage1.setDestination(source); //car voyage aller-retour
        tripResume.addStage(planeStage1);
        tripResume.updateTripStep();

        assertEquals(tripResume.getStages().get(1).getSource().getCityName(),tripResume.getStages().get(0).getDestination().getCityName());
    }

    @AfterEach
    public void undefTripResumeAndStage(){
        tripResume = null;
        planeStage = null;
        hotelStage = null;
        destination = null;
        source = null;
    }
}
