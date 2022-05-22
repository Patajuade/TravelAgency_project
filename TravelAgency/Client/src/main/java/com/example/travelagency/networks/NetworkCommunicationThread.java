package com.example.travelagency.networks;

import be.helha.common.models.TripResume;
import be.helha.common.models.TripsResume;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;
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
        while(isRunning){
            try {
                List<TripResume> trips =  objectSocket.read();
                if(trips != null){
                    listener.getTrips(trips);
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
        void getTrips(List<TripResume> trips);
    }
}
