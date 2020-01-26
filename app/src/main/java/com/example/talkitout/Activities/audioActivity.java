package com.example.talkitout.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.example.talkitout.Classes.Pipeline;
import com.example.talkitout.R;

public class audioActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 0;
    TextView mTextTv;
    ImageButton mVoiceBtn;
    Button doneButton  = null;
    String speech = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mTextTv = findViewById(R.id.textTv);
        doneButton = findViewById(R.id.doneButton);
        mVoiceBtn = findViewById(R.id.voiceBtn);

        doneButton.setOnClickListener(onClickSubmitButton);
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

    private Button.OnClickListener onClickSubmitButton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            submit();
        }
    };

    private void submit() {
        Intent intent = new Intent(this, selectInput.class);
        if (speech.trim().equals("")){
            startActivity(intent);
            return;
        }

        Pipeline p = new Pipeline();
        Integer intensity = p.connectToServer(speech);

        if (intensity != 0){
            Date c = Calendar.getInstance().getTime();
            Random rand = new Random();
            Integer x = rand.nextInt(100);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            MainActivity.DBHelper.addMoodData(x,MainActivity.loggedInUser, speech,
                                            intensity, formattedDate);
            Toast.makeText(this,  "The mood is : " +  intensity, Toast.LENGTH_SHORT).show();
            startActivity(intent);
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
                    speech += result.get(0);
                }
                break;
            }
        }
    }
}
