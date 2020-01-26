package com.example.talkitout.Activities;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talkitout.Classes.Pipeline;
import com.example.talkitout.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class textActivity extends AppCompatActivity {
    EditText textEditText;
    Button doneButton  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        textEditText = findViewById(R.id.textEditText);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(onClickSubmitButton);
    }

    private Button.OnClickListener onClickSubmitButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            submit();
        }
    };

    private void submit() {
        Intent intent = new Intent(this, selectInput.class);
        Pipeline p = new Pipeline();
        Integer intensity = p.connectToServer((textEditText.getText().toString()));
        if (intensity != 0){
            Date c = Calendar.getInstance().getTime();
            Random rand = new Random();
            Integer x = rand.nextInt(100);
            System.out.println(x);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            MainActivity.DBHelper.addMoodData(x,MainActivity.loggedInUser, (textEditText.getText().toString()),intensity, formattedDate);
            Toast.makeText(this,  "The mood is : " +  intensity, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}
