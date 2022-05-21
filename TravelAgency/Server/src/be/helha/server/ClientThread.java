package be.helha.server;

import be.helha.common.models.*;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;

public class ClientThread extends Thread {

    private final ObjectSocket objectSocket;
    private Server server;

    public ClientThread(Server server, ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            TripsResume tripsResume = objectSocket.read();
            System.out.println("Nouvelle instance de connecté");
            while (true){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(Object object) throws IOException {
        objectSocket.write(object);
    }
}
