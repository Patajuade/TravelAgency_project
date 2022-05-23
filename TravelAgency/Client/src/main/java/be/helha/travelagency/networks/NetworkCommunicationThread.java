package be.helha.travelagency.networks;

import be.helha.common.interfaces.Visitor;
import be.helha.common.messages.*;
import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NetworkCommunicationThread extends Thread{
    private final Listener listener;
    private ObjectSocket objectSocket;
    private boolean isRunning = true;

    public NetworkCommunicationThread(ObjectSocket objectSocket, Listener listener) {
        this.objectSocket = objectSocket;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            objectSocket.write(new LoginMessage(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(isRunning){
            try {
                AbstractMessage message = objectSocket.read();
                message.accept(new Visitor() {
                    @Override
                    public void visitLoginMessage(LoginMessage loginMessage) throws IOException {
                        listener.setList(((LoginMessage) message).getTrips());
                    }

                    @Override
                    public void visitDisconnectMessage(DisconnectMessage disconnectMessage) throws IOException {
                        stopRunning();
                    }

                    @Override
                    public void visitAddTripMessage(AddTripMessage addTripMessage) throws IOException {
                        listener.setList(((AddTripMessage) message).getTrips());
                    }

                    @Override
                    public void visitSaveTripsMessage(SaveTripsMessage saveTripsMessage) throws IOException {
                        listener.getTrips(((SaveTripsMessage) message).getTrips());
                    }

                    @Override
                    public void visitUpdateTripsMessage(UpdateTripsMessage updateTripsMessage) {
                        listener.setList(((UpdateTripsMessage) message).getTrips());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            objectSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRunning(){
        isRunning = false;
    }

    public interface Listener {
        void getTrips(ArrayList<TripResume> trips);
        void setList(ArrayList<TripResume> trips);
    }
}
