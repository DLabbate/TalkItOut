package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.Classes.Practitioner;
import com.example.talkitout.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected Button loginButton = null;
    protected Button registerButton = null;
    protected EditText userEditText;
    protected EditText passEditText;

    public static DatabaseHelper DBHelper;
    public static ArrayList<Client> clients;
    public static ArrayList<Practitioner> practitioners;

    public enum status  {
        Practitioner,
        Client
    };

    public static String loggedInUser = "";
    public static status loggedInStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLoginButton);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(onClickRegisterButton);

        userEditText = findViewById(R.id.userEditText);
        passEditText = findViewById(R.id.passEditText);

        DBHelper = new DatabaseHelper(MainActivity.this);
        clients = DBHelper.getAllClients();
        practitioners = DBHelper.getAllPractitioners();

//        //**TEST CODE*************************************************************
//        DBHelper.addClientData("dlabbate","Domenic Labbate","mypassword","1234");
//        DBHelper.addClientData("mgrande","MGrande","hispassword","4321");
//        ArrayList<Client> testclients = DBhelper.getAllClients();
//        for (int i=0 ; i<testclients.size(); i++)
//        {
//            String username = testclients.get(i).getUsername();
//            String name = testclients.get(i).getName();
//            String password = testclients.get(i).getPassword();
//            String practitioner = testclients.get(i).getPractitioner();
//            System.out.println("*****USERNAME: "+username + " NAME: " +name+ " PASSWORD: " +password + " PRACTITIONER " +practitioner);
//        }

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

        String usernameInput = userEditText.getText().toString();
        String passwordInput = passEditText.getText().toString();

        for (int i=0 ; i<clients.size(); i++) {
            Client current = clients.get(i);
            if (current.getUsername().equals(usernameInput) &&
                    current.getPassword().equals(passwordInput)) {
                this.loggedInUser = usernameInput;
                this.loggedInStatus = status.Client;
                startActivity(intent);
                return;
            }
        }

        for (int i=0 ; i<practitioners.size(); i++) {
            Practitioner current = practitioners.get(i);
            if (current.getUsername().equals(usernameInput) &&
                    current.getPassword().equals(passwordInput)) {
                this.loggedInUser = usernameInput;
                this.loggedInStatus = status.Practitioner;
                startActivity(intent);
                return;
            }
        }

        Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
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
