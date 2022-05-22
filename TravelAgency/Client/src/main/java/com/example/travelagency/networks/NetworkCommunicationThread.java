package com.example.travelagency.networks;

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
                if(message instanceof LoginMessage){
                    listener.setList(((LoginMessage) message).getTrips());
                }
                else if(message instanceof SaveTripsMessage){
                    listener.getTrips(((SaveTripsMessage) message).getTrips());
                }
                else if(message instanceof UpdateTripsMessage){
                    listener.setList(((UpdateTripsMessage) message).getTrips());
                }
                else if(message instanceof AddTripMessage){
                    listener.setList(((AddTripMessage) message).getTrips());
                }
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
