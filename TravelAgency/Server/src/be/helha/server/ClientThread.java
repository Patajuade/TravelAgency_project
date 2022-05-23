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
     * @param server is this server
     * @param objectSocket is this objectSocket
     */
    public ClientThread(Server server, ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
        this.server = server;
    }

    /**
     * run method
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
     * write method
     * @param object is the object to write
     * @throws IOException management of input/output exceptions.
     */
    public void write(Object object) throws IOException {
        objectSocket.write(object);
    }
}
