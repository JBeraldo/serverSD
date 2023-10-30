package com.sd.server.threads;

import com.sd.server.actions.ActionHandler;
import com.sd.server.packages.BasePackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread extends Thread
{
    protected Socket socket;

    public static String ip;
    public SocketThread (Socket socket,ThreadGroup tgp,String name)
    {
        super(tgp,name);
        this.socket = socket;
        setIp(socket);
        start();
    }

    private static void setIp(Socket skt){
        ip = skt.getRemoteSocketAddress().toString();
    }

    public void run()
    {
        System.out.println ("New Communication Thread Started");

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( socket.getInputStream()));

            String request;

            while ((request = in.readLine()) != null)
            {
                BasePackage response  = ActionHandler.dispatch(request);
                out.println(response.toJson());
            }

            out.close();
            in.close();
            socket.close();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
