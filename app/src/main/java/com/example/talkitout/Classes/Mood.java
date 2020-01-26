package com.example.talkitout.Classes;
import java.sql.Timestamp;
import java.util.Date;

public class Mood {
    String message;
    int intensity;
    String client_username;
    int id;
    //Date date;


    public Mood(Integer id, String client_username, String message, int intensity)
    {
        //this.date = date;
        this.message = message;
        this.intensity = intensity;
        this.id = id;
        this.client_username = client_username;
    }
}
