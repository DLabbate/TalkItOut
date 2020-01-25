package com.example.talkitout;

import java.util.ArrayList;

public class Client extends Person {

    protected ArrayList<Mood> moods;

    Client(String id,String username,String name,String password)
    {
        super(id,username,name,password);
        moods = new ArrayList<Mood>();
    }

    public void addMood(Mood mood)
    {
        moods.add(mood); //Append a new mood to the list of the clients moods
    }
}
