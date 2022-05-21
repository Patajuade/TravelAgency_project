package com.example.travelagency.networks;

import be.helha.common.networks.ObjectSocket;


public class NetworkCommunicationThread extends Thread{
    private final Listener listener;
    private ObjectSocket objectSocket;

    public NetworkCommunicationThread(ObjectSocket objectSocket, Listener listener) {
        this.objectSocket = objectSocket;
        this.listener = listener;
    }

    @Override
    public void run() {

    }

    public interface Listener {
        void Woof();
    }
}
