package com.example.travelagency;

import com.example.travelagency.models.TripResume;
import com.example.travelagency.models.TripsResume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripsResumeTest {
    TripsResume tripsResume;
    private TripResume tripResume;


    @BeforeEach
    public void setUp(){
        tripsResume = new TripsResume();
        tripResume = new TripResume();
    }

    @Test
    public void addTripResume(){
        tripsResume.addTripResume(tripResume);
        tripsResume.addTripResume(tripResume);
        ArrayList<TripResume> trips = tripsResume.getTrips();
        assertEquals(trips.size(),2);
    }

    @Test
    public void removeTripResume(){
        tripsResume.removeTripResume(tripResume);
        tripsResume.removeTripResume(tripResume);
        ArrayList<TripResume> trips = tripsResume.getTrips();
        assertEquals(trips.size(),0);
    }
}
