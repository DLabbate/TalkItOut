package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected Button loginButton = null;
    protected Button registerButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLoginButton);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(onClickRegisterButton);

        //**TEST CODE*************************************************************
        DatabaseHelper DBhelper = new DatabaseHelper(MainActivity.this);
        DBhelper.addClientData("dlabbate","Domenic Labbate","mypassword","1234");
        DBhelper.addClientData("mgrande","MGrande","hispassword","4321");
        ArrayList<Client> testclients = DBhelper.getAllClients();
        for (int i=0 ; i<testclients.size(); i++)
        {
            String username = testclients.get(i).getUsername();
            String name = testclients.get(i).getName();
            String password = testclients.get(i).getPassword();
            String practitioner = testclients.get(i).getPractitioner();
            System.out.println("*****USERNAME: "+username + " NAME: " +name+ " PASSWORD: " +password + " PRACTITIONER " +practitioner);
        }

        //************************************************************************
    }

    private Button.OnClickListener onClickLoginButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            login();
        }
    };

    private void login() {
        Intent intent = new Intent(this, selectInput.class);
        startActivity(intent);
    }

    private Button.OnClickListener onClickRegisterButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            register();
        }
    };

    private void register() {
        Intent intent = new Intent(this, registerActivity.class);
        startActivity(intent);
    }



}
