package be.helha.server;

import be.helha.common.interfaces.Visitor;
import be.helha.common.messages.*;
import be.helha.common.networks.ObjectSocket;

import java.io.IOException;

/**
 * Client Thread
 */
public class ClientThread extends Thread {

    private final ObjectSocket objectSocket;
    private Server server;
    private boolean isRunning = true;

    /**
     * @param server is this server
     * @param objectSocket is this objectSocket
     */
    public ClientThread(Server server, ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
        this.server = server;
    }

    /**
     * Puts the server in listening mode by creating a listening thread for each client
     * Management of double dispatch
     */
    @Override
    public void run() {
        try {
            while (isRunning){
                AbstractMessage abstractMessage = objectSocket.read();
                abstractMessage.accept(new Visitor() {
                    @Override
                    public void visitLoginMessage(LoginMessage loginMessage) throws IOException {
                        objectSocket.write(new LoginMessage(server.getTrips()));
                    }

                    @Override
                    public void visitDisconnectMessage(DisconnectMessage disconnectMessage) throws IOException {
                        objectSocket.write(abstractMessage);
                        stopRunning();
                        server.deconnect(ClientThread.this);
                    }

                    @Override
                    public void visitAddTripMessage(AddTripMessage addTripMessage) throws IOException {
                        server.saveTripsResume(((AddTripMessage) abstractMessage).getTrips());
                        server.butBroadcast(new AddTripMessage(server.getTrips()),ClientThread.this);
                    }

                    @Override
                    public void visitSaveTripsMessage(SaveTripsMessage saveTripsMessage) throws IOException {
                        server.saveTripsResume(((SaveTripsMessage) abstractMessage).getTrips());
                        server.broadcast(new UpdateTripsMessage(server.getTrips()));
                    }

                    @Override
                    public void visitUpdateTripsMessage(UpdateTripsMessage updateTripsMessage) {
                        server.broadcast(new UpdateTripsMessage(server.getTrips()));
                    }
                });
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

    /**
     * Stops the client dedicated thread
     */
    private void stopRunning(){
        isRunning = false;
    }
}
