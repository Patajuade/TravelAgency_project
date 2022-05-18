package com.example.travelagency;

import com.example.travelagency.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TripResumeTest {

    private TripResume tripResume;
    private static HotelStage hotelStage;
    private static PlaneStage planeStage;
    private static CityModel destination;
    private static CityModel source;

    @BeforeAll
    public static void setUp(){
        hotelStage = new HotelStage();
        planeStage = new PlaneStage();
        destination = new CityModel("destination","10","10","a");
        source = new CityModel("source","20","20","b");

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

    @BeforeEach
    public void setUp_beforeEach(){
        tripResume = new TripResume();
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

    @AfterEach
    public void undefTripResumeAndStage(){
        tripResume = null;
        planeStage = null;
        hotelStage = null;
        destination = null;
        source = null;
    }
}
