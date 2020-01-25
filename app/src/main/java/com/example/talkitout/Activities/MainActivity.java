package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.talkitout.R;

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
