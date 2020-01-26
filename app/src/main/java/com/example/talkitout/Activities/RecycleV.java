package com.example.talkitout.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.talkitout.Classes.Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.talkitout.R;

import java.util.ArrayList;

public class RecycleV extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<Client> mClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mClients = new ArrayList<>();
        for (Client c : MainActivity.clients){
            if (c.getPractitioner().equals(MainActivity.loggedInUser)) {
                mClients.add(c);
            }
        }
//        Client first = new Client("abc", "def", "ghi", "lmo");
//        mClients.add(first);
//        mClients.add(first);
//        mClients.add(first);
//        mClients.add(first);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ClientAdapter(mClients);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void transitionToGraph(String clientUsername){
        Intent intent = new Intent(this, graphActivity.class);
        startActivity(intent);
    }


}
