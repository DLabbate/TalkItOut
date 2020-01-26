package com.example.talkitout.Classes;

import java.io.*;
import java.net.*;

public class Pipeline
{
    PrintStream streamToServer;
    BufferedReader streamFromServer;
    Socket toServer;

    public Pipeline(String message)
    {
        connectToServer(message);
    }

    private void connectToServer(String message)
    {
        try{
            toServer = new Socket("172.30.190.229", 6789);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            streamToServer.println(message);
            String str = streamFromServer.readLine();
            System.out.println("The Server Says "+str);
        }
        catch(Exception e)
        {
            System.out.println("Exception "+e);
        }
    }
}
