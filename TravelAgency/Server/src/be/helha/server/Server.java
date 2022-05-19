package be.helha.server;

import be.helha.common.networks.ObjectSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.go();
    }

    List<ClientThread> threads = new ArrayList<>();

    private void go() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1099);
        while(true) {
            ObjectSocket os = new ObjectSocket(serverSocket.accept());
            ClientThread thread = new ClientThread(this,os);
            thread.start();
            synchronized (threads) {
                threads.add(thread);
            }
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

    public void deconnect(ClientThread clientThread) {
        synchronized (threads) {
            threads.remove(clientThread);
        }
    }
}
