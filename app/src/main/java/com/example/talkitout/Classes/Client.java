package com.example.talkitout.Classes;

import java.util.ArrayList;

public class Client extends Person {

    protected ArrayList<Mood> moods;
    String practitioner;

    public Client(String username,String name,String password,String practitioner)
    {
        super(username,name,password);
        moods = new ArrayList<Mood>();
        this.practitioner = practitioner;
    }

    public void addMood(Mood mood)
    {
        moods.add(mood); //Append a new mood to the list of the clients moods
    }

    public String getPractitioner()
    {
        return practitioner;
    }

    public void setPractitioner(String practitioner)
    {
        this.practitioner = practitioner;
    }
}
