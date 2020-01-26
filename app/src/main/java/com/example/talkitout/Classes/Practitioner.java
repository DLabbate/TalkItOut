package com.example.talkitout.Classes;

import com.example.talkitout.Classes.Person;

import java.util.ArrayList;

public class Practitioner extends Person {

    ArrayList<Client> clients;

    public Practitioner(String username,String name,String password)
    {
        super(username,name,password);
        clients = new ArrayList<Client>();
    }

    public void addNewClient(Client client)
    {
        clients.add(client);
    }


}
