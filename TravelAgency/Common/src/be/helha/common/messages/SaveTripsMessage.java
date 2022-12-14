package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;
import be.helha.common.models.TripResume;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Save list trip message
 */
public class SaveTripsMessage extends AbstractMessage {
    public SaveTripsMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    ArrayList<TripResume> trips;
    @Override
    public void accept(Visitor v) throws IOException {
        v.visitSaveTripsMessage(this);
    }
}
