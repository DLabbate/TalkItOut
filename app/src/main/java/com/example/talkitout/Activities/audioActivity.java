package com.example.talkitout.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Locale;

import com.example.talkitout.R;

public class audioActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 0;
    TextView mTextTv;
    ImageButton mVoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mTextTv = findViewById(R.id.textTv);
        mVoiceBtn = findViewById(R.id.voiceBtn);

        //Clicking this button will open the speech dialog.
        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                speak();
            }
        });
    }
    private void speak(){
        Intent speechRec = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRec.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                           RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRec.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRec.putExtra(RecognizerIntent.EXTRA_PROMPT, "How are you feeling today?");

        try {
            startActivityForResult(speechRec, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    // Convert voice intent into text array.
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Set it into the text view.
                    mTextTv.setText(result.get(0));
                }
                break;
            }
        }
    }
}
