package be.helha.common.messages;

import be.helha.common.models.TripResume;

import java.util.ArrayList;

public class SaveTripsMessage extends AbstractMessage {
    public SaveTripsMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    ArrayList<TripResume> trips;
}
