package com.crazy4web.implicitintent_lab;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {


    private static List<String> mUrl;
    private Context mcon;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public LinearLayout linearLayout;


        public MyViewHolder(View v) {

            super(v);
            textView = v.findViewById(R.id.my_text_view);
            linearLayout = v.findViewById(R.id.linearlayout);


        }
    }


    public MyAdaptor(Context con, List<String> myUrl ) {


        mUrl = myUrl;
        mcon = con;

    }






    @Override
    public MyAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);



        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {



        holder.textView.setText(mUrl.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i = new Intent("message");
            i.putExtra("url",mUrl.get(position).toString());

            LocalBroadcastManager.getInstance(mcon).sendBroadcast(i);



            }
        });



    }



    public int getItemCount() {

        return mUrl.size();

    }}

