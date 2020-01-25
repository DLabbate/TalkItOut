package com.example.talkitout.Classes;

import com.example.talkitout.Classes.Person;

import java.util.ArrayList;

public class Practitioner extends Person {

    ArrayList<Client> clients;

    public Practitioner(String id,String username,String name,String password)
    {
        super(id,username,name,password);
        clients = new ArrayList<Client>();
    }

}
