package be.helha.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for TripsResume
 * Class to make a list of TripResume, which are complete single trips, each trip containing multiple HotelStages and PlaneStages.
 */
public class TripsResume implements Serializable {
    public ArrayList<TripResume> getTrips() {
        return trips;
    }

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

    /**
     * Getter allowing to use trips outside this class.
     * @return a list of trips
     */
    public void setTrips(ArrayList<TripResume> tripsList) {
        trips = tripsList;
    }
}
