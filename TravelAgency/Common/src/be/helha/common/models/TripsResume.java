package be.helha.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TripsResume implements Serializable {
    public ArrayList<TripResume> getTrips() {
        return trips;
    }

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

    public void setTrips(ArrayList<TripResume> tripsList) {
        trips = tripsList;
    }
}
