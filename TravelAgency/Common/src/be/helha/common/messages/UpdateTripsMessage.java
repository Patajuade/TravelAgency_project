package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;
import be.helha.common.models.TripResume;

import java.util.ArrayList;

public class UpdateTripsMessage extends AbstractMessage{
    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    public UpdateTripsMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    ArrayList<TripResume> trips;
    @Override
    public void accept(Visitor v) {
        v.visitUpdateTripsMessage(this);
    }
}
