package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.talkitout.R;

public class  registerActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    CheckBox usertypeCheckBox;
    EditText nameEditText,usernameEditText,passwordEditText,practitionerEditText;
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

    private void setupUI()
    {
        signUpButton = findViewById(R.id.signupButton);
        usertypeCheckBox = findViewById(R.id.usercheckBox);
        nameEditText = findViewById(R.id.fullnameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        practitionerEditText = findViewById(R.id.practitionerEditText);
    }

    private void setUpOnClickListeners()
    {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (usertypeCheckBox.isChecked())
                {
                    //TO DO
                    //ADD NEW PRACTITIONER
                    //Verify new practitioner username is not taken
                    Intent gotoDisplay = new Intent(registerActivity.this,displayActivity.class);
                    startActivity(gotoDisplay);
                }

                else
                {
                    //ADD NEW CLIENT
                    //Verify new client username is not taken
                    String practitioner = practitionerEditText.getText().toString();
                    myDB.addClientData(username,name,password,practitioner);
                    Intent gotoselectInput = new Intent(registerActivity.this,selectInput.class);
                    startActivity(gotoselectInput);
                }
            }
        });

        usertypeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usertypeCheckBox.isChecked())
                {
                    practitionerEditText.setVisibility(View.INVISIBLE);
                }
                else
                {
                    practitionerEditText.setVisibility(View.VISIBLE);
                }
            }
        });
    }



}
