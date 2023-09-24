package com.example.server.threads;

import com.example.server.actions.ActionHandler;
import com.example.server.packages.BasePackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread extends Thread
{
    protected static boolean serverContinue = true;
    protected Socket clientSocket;

    protected ActionHandler actionHandler = new ActionHandler();

    public SocketThread (Socket clientSoc)
    {
        clientSocket = clientSoc;
        start();
    }

    public void run()
    {
        System.out.println ("New Communication Thread Started");

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( clientSocket.getInputStream()));

            String request;

            while ((request = in.readLine()) != null)
            {
                BasePackage response  = actionHandler.dispatch(request);
                out.println(response.toJson());
            }

            out.close();
            in.close();
            clientSocket.close();
            return ;
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
