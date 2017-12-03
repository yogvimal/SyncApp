package com.example.yogi.syncapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOGI on 10-11-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;
    private Context context;

    public RecyclerAdapter(ArrayList<Contact> arrayList)
    {
        contacts = arrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        int sync_status = contacts.get(position).getSync_Status();
        if(sync_status==DbContract.SYNC_STATUS_OK)
        {
            holder.sync_status.setImageResource(R.drawable.ic_done_all_black_24dp);
        }
        else
        {
            holder.sync_status.setImageResource(R.drawable.ic_done_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sync_status;
        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name = view.findViewById(R.id.textView);
            sync_status = view.findViewById(R.id.imageView);
        }
    }
}
