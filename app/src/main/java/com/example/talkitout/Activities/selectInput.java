package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.talkitout.R;

public class selectInput extends AppCompatActivity {

    //protected Button audioButton = null;
    protected Button textButton = null;
    protected ImageView audioImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_input);

        //audioButton = (Button) findViewById(R.id.audioButton);
        //audioButton.setOnClickListener(onClickAudioButton);

        textButton = (Button) findViewById(R.id.textButton);
        textButton.setOnClickListener(onClickTextButton);

        audioImage = (ImageView) findViewById(R.id.audioImageView);
        audioImage.setOnClickListener(onClickAudioImage);
    }


    private Button.OnClickListener onClickAudioImage = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            audio();
        }
    };

    private void audio() {
        Intent intent = new Intent(this, audioActivity.class);
        startActivity(intent);
    }

    private Button.OnClickListener onClickTextButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            text();
        }
    };

    private void text() {
        Intent intent = new Intent(this, textActivity.class);
        startActivity(intent);
    }
}
