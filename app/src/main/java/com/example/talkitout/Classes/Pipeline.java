package com.example.talkitout.Classes;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.*;
import java.net.*;

public class Pipeline
{
    public static PrintStream streamToServer;
    public static BufferedReader streamFromServer;
    public static Socket toServer;

    public Pipeline()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static Integer connectToServer(String message)
    {
        try{
            toServer = new Socket("172.30.190.229", 6789);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            streamToServer.println(message);
            String str = streamFromServer.readLine();
            System.out.println("The Server Says "+str);
            return Integer.parseInt(str);
        }
        catch(Exception e)
        {
            System.out.println("Exception "+e);
        }
        return 0;
    }
}
