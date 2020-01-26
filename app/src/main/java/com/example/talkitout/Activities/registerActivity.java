package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.talkitout.R;

public class  registerActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd, btnView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewByID(R.id.btnAdd);
        */
    }

}
