package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.text.method.PasswordTransformationMethod;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.Classes.Practitioner;
import com.example.talkitout.R;

import java.util.ArrayList;

public class  registerActivity extends AppCompatActivity {

    CheckBox usertypeCheckBox;
    EditText nameEditText, usernameEditText, passwordEditText, practitionerEditText;
    Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        passwordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());
    }

    private void setUpOnClickListeners() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!isNameAvailable(username)) {
                    displayMessage("Username unavailable.");
                    return;
                }

                if (name == null || name.equals("")
                || username == null || username.equals("")
                || password == null || password.equals("")){
                    displayMessage("Please enter a name, username, and password.");
                    return;
                }

                if (usertypeCheckBox.isChecked()) {
                    //ADD NEW PRACTITIONER
                    MainActivity.DBHelper.addPractitionerData(username, password, name);
                }

                else {
                    //ADD NEW CLIENT
                    String practitioner = practitionerEditText.getText().toString();
                    MainActivity.DBHelper.addClientData(username, name, password, practitioner);
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
                return false;
            }
        }

        for (int i = 0; i < MainActivity.practitioners.size(); i++) {
            Practitioner current = MainActivity.practitioners.get(i);
            if (current.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private void displayMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

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
