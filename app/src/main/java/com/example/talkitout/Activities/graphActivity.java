package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.talkitout.Classes.Mood;
import com.example.talkitout.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    public static String currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        double y;
        //x = -5.0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();

        for(int i = 0; i<MainActivity.moods.size(); i++){
            if(MainActivity.moods.get(i).getClient_username() == currentClient){
                y = MainActivity.moods.get(i).getIntensity();
                //Mood current = moods(i);
                series.appendData(new DataPoint(i,y), true, 7);
            }
        }

        graph.addSeries(series);
    }
}
