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
        System.out.println(currentClient);

        double y;
        //x = -5.0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(7);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(5);
        Integer count=0;
        for(int i = 0; i<MainActivity.moods.size(); i++){
            if(MainActivity.moods.get(i).getClient_username().equals(currentClient)){
                y = MainActivity.moods.get(count).getIntensity();
                //Mood current = moods(i);
                series.appendData(new DataPoint(count, y), true, 7);
                count++;
            }
        }

        graph.addSeries(series);
    }
}
