package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;
import be.helha.common.models.TripResume;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Login request
 */
public class LoginMessage extends AbstractMessage{

    public LoginMessage(ArrayList<TripResume> trips) {
        this.trips = trips;
    }

    public ArrayList<TripResume> getTrips() {
        return trips;
    }

    private ArrayList<TripResume> trips;
    @Override
    public void accept(Visitor v) throws IOException {
        v.visitLoginMessage(this);
    }
}
