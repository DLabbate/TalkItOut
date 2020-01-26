package com.example.talkitout.Classes;
import java.sql.Timestamp;
import java.util.Date;

public class Mood {
    String message;
    int intensity;
    String client_username;
    int id;
    String date;
    //Date date;


    public Mood(Integer id, String client_username, String message, int intensity, String date)
    {
        //this.date = date;
        this.message = message;
        this.intensity = intensity;
        this.id = id;
        this.client_username = client_username;
        this.date = date;
    }

    public int getIntensity(){
        return intensity;
    }

    public String getClient_username()
    {
        return client_username;
    }
}
