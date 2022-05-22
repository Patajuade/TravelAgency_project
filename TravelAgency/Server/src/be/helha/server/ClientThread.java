package be.helha.server;

import be.helha.common.messages.*;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;

/**
 * Client Thread
 */
public class ClientThread extends Thread {

    private final ObjectSocket objectSocket;
    private Server server;

    /**
     * @param server
     * @param objectSocket
     */
    public ClientThread(Server server, ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
        this.server = server;
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            while (true){
                AbstractMessage abstractMessage = objectSocket.read();
                if(abstractMessage instanceof LoginMessage){
                    objectSocket.write(new LoginMessage(server.getTrips()));
                }
                else if(abstractMessage instanceof SaveTripsMessage){
                    server.saveTripsResume(((SaveTripsMessage) abstractMessage).getTrips());
                    server.broadcast(new UpdateTripsMessage(server.getTrips()));
                }
                else if(abstractMessage instanceof UpdateTripsMessage){
                    server.broadcast(new UpdateTripsMessage(server.getTrips()));
                }
                else if(abstractMessage instanceof AddTripMessage){
                    server.saveTripsResume(((AddTripMessage) abstractMessage).getTrips());
                    server.butBroadcast(new AddTripMessage(server.getTrips()),this);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param object
     * @throws IOException
     */
    public void write(Object object) throws IOException {
        objectSocket.write(object);
    }
}
