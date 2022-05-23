package be.helha.travelagency.networks;

import be.helha.common.interfaces.Visitor;
import be.helha.common.messages.*;
import be.helha.common.models.TripResume;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Thread class listening for client
 */
public class NetworkCommunicationThread extends Thread{
    private final Listener listener;
    private ObjectSocket objectSocket;
    private boolean isRunning = true;

    /**
     * Class constructor
     * @param objectSocket objectSocket
     * @param listener listener
     */
    public NetworkCommunicationThread(ObjectSocket objectSocket, Listener listener) {
        this.objectSocket = objectSocket;
        this.listener = listener;
    }

    /**
     * Connects the client and gets current list from server
     */
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
                    /**
                     * Management of abstract messages with double dispatch
                     */
                    @Override
                    public void visitLoginMessage(LoginMessage loginMessage) throws IOException {
                        listener.updateList(((LoginMessage) message).getTrips());
                    }

                    @Override
                    public void visitDisconnectMessage(DisconnectMessage disconnectMessage) throws IOException {
                        stopRunning();
                    }

                    @Override
                    public void visitAddTripMessage(AddTripMessage addTripMessage) throws IOException {
                        listener.updateList(((AddTripMessage) message).getTrips());
                    }

                    @Override
                    public void visitSaveTripsMessage(SaveTripsMessage saveTripsMessage) throws IOException {
                        listener.getListFromServer(((SaveTripsMessage) message).getTrips());
                    }

                    @Override
                    public void visitUpdateTripsMessage(UpdateTripsMessage updateTripsMessage) {
                        listener.updateList(((UpdateTripsMessage) message).getTrips());
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

    /**
     * Stops the listening thread
     */
    public void stopRunning(){
        isRunning = false;
    }

    public interface Listener {
        /**
         * Gets trips on the server
         * @param trips trips list
         */
        void getListFromServer(ArrayList<TripResume> trips);

        /**
         * Gets the list from server
         * @param trips trips list
         */
        void updateList(ArrayList<TripResume> trips);
    }
}
