package com.example.talkitout.Classes;

import java.io.*;
import java.net.*;

public class Pipeline
{
    public static PrintStream streamToServer;
    public static BufferedReader streamFromServer;
    public static Socket toServer;

    public Pipeline(String message)
    {
    }

    public static Integer connectToServer(String message)
    {
        if (message.equals("")){
            return 0;
        }
        try{
            toServer = new Socket("172.30.190.229", 6789);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            streamToServer.println(message);
            String str = streamFromServer.readLine();
            Integer mood = Integer.parseInt(str);
            return mood;
        }
        catch(Exception e)
        {
            System.out.println("Exception "+e);
        }
        return 0;
    }
}
