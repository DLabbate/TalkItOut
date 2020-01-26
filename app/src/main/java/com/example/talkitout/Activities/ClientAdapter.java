package com.example.talkitout.Activities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.talkitout.Classes.Client;
import com.example.talkitout.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder>{

    private ArrayList<Client> mClients;

    public ClientAdapter(ArrayList<Client> clients) {
        mClients = clients;
    }

    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientAdapter.ViewHolder holder, int position) {
        holder.mBtn.setText(mClients.get(position).getUsername());
        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }

        });

    }

    @Override
    public int getItemCount() {
        return mClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button mBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            mBtn = itemView.findViewById(R.id.clickable);
        }
    }
}
