package com.example.talkitout.Classes;

public class Person {
    protected String id;
    protected String username;
    protected String name;
    protected String password;

    public Person(String id,String username,String name,String password)
    {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
