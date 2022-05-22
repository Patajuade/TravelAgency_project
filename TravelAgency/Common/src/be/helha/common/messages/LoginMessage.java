package be.helha.common.messages;

import be.helha.common.models.TripResume;

import java.util.ArrayList;

public class LoginMessage extends AbstractMessage{

    public LoginMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    private ArrayList<TripResume> trips;
}
