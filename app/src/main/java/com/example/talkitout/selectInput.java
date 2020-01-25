package com.example.talkitout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectInput extends AppCompatActivity {

    protected Button audioButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_input);

        audioButton = (Button) findViewById(R.id.audioButton);
        audioButton.setOnClickListener(onClickAudioButton);
    }


    private Button.OnClickListener onClickAudioButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            audio();
        }
    };

    private void audio() {
        Intent intent = new Intent(this, audioActivity.class);
        startActivity(intent);
    }
}
