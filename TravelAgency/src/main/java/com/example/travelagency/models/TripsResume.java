package com.example.travelagency.models;
import java.util.ArrayList;

/**
 * Model for TripsResume
 * Class to make a list of TripResume, which are complete single trips, each trip containing multiple HotelStages and PlaneStages.
 */
public class TripsResume {
    ArrayList<TripResume> trips;

    /**
     * Default constructor
     */
    public TripsResume() {
        trips = new ArrayList<>();
    }

    /**
     * Method allowing to add a trip to the trips list
     * @param tripResume is a complete trip
     */
    public void addTripResume(TripResume tripResume){
        trips.add(tripResume);
    }

    /**
     * Method allowing to remove a trip from the trips list
     * @param tripResume is a complete trip
     */
    public void removeTripResume(TripResume tripResume){
        trips.remove(tripResume);
    }
}
