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

public class Server {
    Scanner scanner = new Scanner(System.in);
    File travels = new File("travels.bin");
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.go();
    }

    List<ClientThread> threads = new ArrayList<>();

    private void go() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1099);
            while(true) {
                System.out.println("En attente de client ...");
                ObjectSocket os = new ObjectSocket(serverSocket.accept());
                System.out.println("Nouveau client accepté");
                ClientThread thread = new ClientThread(this,os);
                thread.start();
                synchronized (threads) {
                    threads.add(thread);
                }
            }
        }catch (IOException e){
            System.out.println("Le serveur n'a pas pu démarrer");
        }
    }

    public void broadcast(Object object) {
        synchronized (threads) {
            for (ClientThread thread : threads) {
                try {
                    thread.write(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeTripsResumeOnRom(Object object) throws IOException {
       try (ObjectOutputStream output = new ObjectOutputStream(
              new FileOutputStream("travels.bin"))) {
            output.writeObject(object);
       }
    }

    public List<TripResume> readTripsResumeFromRom(){
        List<TripResume> trips = new ArrayList<>();
        if(Files.exists(Path.of("travels.bin"))){
            try{
                ObjectInputStream input = new ObjectInputStream(new FileInputStream("travels.bin"));
                trips = (List<TripResume>) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

    public void deconnect(ClientThread clientThread) {
        synchronized (threads) {
            threads.remove(clientThread);
        }
    }
}
