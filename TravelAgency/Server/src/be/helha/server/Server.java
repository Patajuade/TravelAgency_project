package be.helha.server;

import be.helha.common.models.TripResume;
import be.helha.common.networks.ObjectSocket;

import java.io.*;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Server Class
 */
public class Server {
    public static void main(String[] args){
        Server server = new Server();
        server.go();
    }

    List<ClientThread> threads = new ArrayList<>();

    /**
     * Starts the server listening on the selected ports
     * for each client requests, creates a client thread
     */
    private void go(){
        try {
            ServerSocket serverSocket = new ServerSocket(1099);
            while(true) {
                System.out.println("En attente de client ...");
                ObjectSocket os = new ObjectSocket(serverSocket.accept());
                ClientThread thread = new ClientThread(this,os);
                thread.start();
                System.out.println("Nouveau client accepté, son ID sera " + thread.getId());
                synchronized (threads) {
                    threads.add(thread);
                }
            }
        }catch (IOException e){
            System.out.println("Le serveur n'a pas pu démarrer");
        }
    }

    /**
     * Broadcasts an object to all client threads
     * @param object object
     */
    public void broadcast(Object object) {
        butBroadcast(object,null);
    }

    /**
     * Broadcasts an object to all client except the one who asked
     * @param object object
     * @param clientThread current client thread
     */
    public void butBroadcast(Object object,ClientThread clientThread){
        synchronized (threads) {
            for (ClientThread thread : threads) {
                try {
                    if(thread != clientThread){
                        thread.write(object);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Modifies database -> travels.bin
     * @param object object
     * @throws IOException management of input/output exceptions.
     */
    public void saveTripsResume(Object object) throws IOException {
       try (ObjectOutputStream output = new ObjectOutputStream(
              new FileOutputStream("travels.bin"))) {
            output.writeObject(object);
       }
    }

    /**
     * reads database (travels.bin) and sends it
     * @return list of tripresume
     */
    public ArrayList<TripResume> getTrips(){
        ArrayList<TripResume> trips = new ArrayList<>();
        if(Files.exists(Path.of("travels.bin"))){
            try{
                ObjectInputStream input = new ObjectInputStream(new FileInputStream("travels.bin"));
                trips = (ArrayList<TripResume>) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

    /**
     * Disconnects a client thread dedicated to a gone client
     * @param clientThread client thread
     */
    public void deconnect(ClientThread clientThread) {
        synchronized (threads) {
            System.out.println("Le client " + clientThread.getId() + " s'est déconnecté");
            threads.remove(clientThread);
        }
    }
}
