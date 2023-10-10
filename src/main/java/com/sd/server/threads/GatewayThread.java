package com.sd.server.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class GatewayThread extends Thread{

    ThreadGroup connections;
    Integer port;
    public GatewayThread(Connections connections,Integer port) {
        this.connections = connections;
        this.port = port;
    }

    public void run() {
        int count = 0;
        List<Thread> threads = new LinkedList<>();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                threads.add(new SocketThread(serverSocket.accept(), connections, Integer.toString(count)));
                count++;
            } catch (IOException e) {
                System.out.println("Connection Closed");
            }
        }
    }
}
