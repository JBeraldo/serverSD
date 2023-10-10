package com.sd.server;


import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.threads.Connections;
import com.sd.server.threads.GatewayThread;

public class Server {

    public static Connections connections = new Connections("connections");
    private static JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();
    private static UserDAO userDAO= new UserDAO();


    public static void start(Integer port) {
        jwtSessionDAO.deleteAllJWTSession();
        userDAO.addFirstUser();
        Thread gateway = new GatewayThread(connections,port);
        gateway.start();
    }
}