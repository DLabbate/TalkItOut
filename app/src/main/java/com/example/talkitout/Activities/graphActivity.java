package com.example.talkitout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.talkitout.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    static String currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        double y,x;
        x = -5.0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int i=0; i<500; i++){
            x = x + 0.1;
            y = x;
            series.appendData(new DataPoint(x,y), true, 500);
        }

        graph.addSeries(series);

    }
}
