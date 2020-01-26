package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talkitout.Classes.Pipeline;
import com.example.talkitout.R;

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
        Integer mood = Pipeline.connectToServer(textEditText.getText().toString());
        if (mood != 0){
            startActivity(intent);
        }
        Toast.makeText(this, textEditText.toString() + " " + mood, Toast.LENGTH_SHORT).show();
    }
}
