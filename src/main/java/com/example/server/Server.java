package com.example.server;


import com.example.server.database.DatabaseService;
import com.example.server.threads.Connections;
import com.example.server.threads.SocketThread;
import org.apache.logging.log4j.LogManager;


import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class Server {

    public static Connections connections = new Connections("connections");

    public static void start(Integer port) {
        DatabaseService.up();
        new Thread(() -> {
            try {
                int count = 0;
                while (true){
                    ServerSocket serverSocket = new ServerSocket(port);
                    new SocketThread(serverSocket.accept(), connections, Integer.toString(count));
                    countConnections();
                    count++;
                }
            } catch (IOException e) {
            }
        }).start();
    }

    //TODO: ver por que quando fica sem nenhuma conexão cai o servidor

    public static void countConnections(){
        System.out.println(connections.activeCount());
    }
}