package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.Classes.Practitioner;
import com.example.talkitout.R;

import java.util.ArrayList;

public class  registerActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    CheckBox usertypeCheckBox;
    EditText nameEditText, usernameEditText, passwordEditText, practitionerEditText;
    Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewByID(R.id.btnAdd);
        */
        setupUI();
        setUpOnClickListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setupUI() {
        signUpButton = findViewById(R.id.signupButton);
        usertypeCheckBox = findViewById(R.id.usercheckBox);
        nameEditText = findViewById(R.id.fullnameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        practitionerEditText = findViewById(R.id.practitionerEditText);
    }

    private void setUpOnClickListeners() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!isNameAvailable(username)) {
                    return;
                }

                if (usertypeCheckBox.isChecked()) {
                    //ADD NEW PRACTITIONER
                    myDB.addPractitionerData(username, name, password);
                    MainActivity.practitioners.add(new Practitioner(username, name, password));
                }

                else {
                    //ADD NEW CLIENT
                    String practitioner = practitionerEditText.getText().toString();
                    myDB.addClientData(username, name, password, practitioner);
                    MainActivity.clients.add(new Client(username, name, password, practitioner));
                }

                Intent gotoDisplay = new Intent(registerActivity.this, MainActivity.class);
                startActivity(gotoDisplay);
            }

        });

        usertypeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usertypeCheckBox.isChecked()) {
                    practitionerEditText.setVisibility(View.INVISIBLE);
                } else {
                    practitionerEditText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private boolean isNameAvailable(String username) {
        for (int i = 0; i < MainActivity.clients.size(); i++) {
            Client current = MainActivity.clients.get(i);
            if (current.getUsername().equals(username)) {
                Toast.makeText(this, "Username unavailable", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        for (int i = 0; i < MainActivity.practitioners.size(); i++) {
            Practitioner current = MainActivity.practitioners.get(i);
            if (current.getUsername().equals(username)) {
                Toast.makeText(this, "Username unavailable", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }



}
