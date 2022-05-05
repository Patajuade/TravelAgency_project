package com.example.travelagency.models;

import java.util.ArrayList;

public class TripsResume {
    ArrayList<TripResume> trips;

    public TripsResume() {
        trips = new ArrayList<>();
    }

    public void addTripResume(TripResume tripResume){
        trips.add(tripResume);
    }

    public void removeTripResume(TripResume tripResume){
        trips.remove(tripResume);
    }
}
