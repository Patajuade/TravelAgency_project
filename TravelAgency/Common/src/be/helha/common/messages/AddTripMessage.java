package be.helha.common.messages;

import be.helha.common.models.TripResume;

import java.util.ArrayList;

public class AddTripMessage extends AbstractMessage{
    public AddTripMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    ArrayList<TripResume> trips;
}
