package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.Classes.Mood;
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
    public static ArrayList<Mood> moods;


    public enum status {
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
        passEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        DBHelper = new DatabaseHelper(MainActivity.this);

        //************************************************************************
    }

    @Override
    protected void onStart() {
        super.onStart();
        userEditText.setText("");
        passEditText.setText("");
        clients = DBHelper.getAllClients();
        practitioners = DBHelper.getAllPractitioners();
        moods = DBHelper.getAllMoods();

//        //**TEST CODE*************************************************************
//        DBHelper.addClientData("dlabbate","Domenic Labbate","mypassword","1234");
//        DBHelper.addClientData("mgrande","MGrande","hispassword","4321");
//        ArrayList<Client> testclients = DBhelper.getAllClients();

        //TEST CODE FOR MOOD********************


        //DBHelper.addMoodData(123, "john", "message", 5, "2020-01-10 12:08:5.5");
        //Date date = Date.valueOf("2020-01-10 12:08:5.5");
        //System.out.println("##############" + date.toString());

        for (int i=0 ; i<moods.size(); i++)
        {
            System.out.println(moods.get(i).getIntensity());
        }

        for (int i=0 ; i<clients.size(); i++)
        {
            String username = clients.get(i).getUsername();
            String name = clients.get(i).getName();
            String password = clients.get(i).getPassword();
            String practitioner = clients.get(i).getPractitioner();
            System.out.println("*****USERNAME: "+username + " NAME: " +name+ " PASSWORD: " +password + " PRACTITIONER " +practitioner);
        }

        for (int i=0 ; i<practitioners.size(); i++)
        {
            String username = practitioners.get(i).getUsername();
            String name = practitioners.get(i).getName();
            String password = practitioners.get(i).getPassword();
            System.out.println("*****USERNAME: "+username + " NAME: " +name+ " PASSWORD: " +password);
        }

    }
    private Button.OnClickListener onClickLoginButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            login();
        }
    };

    private void login() {
        Intent intent = new Intent(this, selectInput.class);
        Intent intent1 = new Intent(this, RecycleV.class);

        String usernameInput = userEditText.getText().toString();
        String passwordInput = passEditText.getText().toString();

        for (int i=0 ; i<clients.size(); i++) {
            Client current = clients.get(i);
            if (current.getUsername().equals(usernameInput) &&
                    current.getPassword().equals(passwordInput)) {
                this.loggedInUser = usernameInput;
                this.loggedInStatus = status.Client;
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                return;
            }
        }

        for (int i=0 ; i<practitioners.size(); i++) {
            Practitioner current = practitioners.get(i);
            if (current.getUsername().equals(usernameInput) &&
                    current.getPassword().equals(passwordInput)) {
                this.loggedInUser = usernameInput;
                this.loggedInStatus = status.Practitioner;
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
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
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        startActivity(intent);
    }

    private class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        public class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };
}
