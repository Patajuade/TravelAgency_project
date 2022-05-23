package be.helha.common.interfaces;

import be.helha.common.messages.*;

import java.io.IOException;

public interface Visitor {
    void visitLoginMessage(LoginMessage loginMessage) throws IOException;
    void visitDisconnectMessage(DisconnectMessage disconnectMessage) throws IOException;
    void visitAddTripMessage(AddTripMessage addTripMessage) throws IOException;
    void visitSaveTripsMessage(SaveTripsMessage saveTripsMessage) throws IOException;
    void visitUpdateTripsMessage(UpdateTripsMessage updateTripsMessage);
}
