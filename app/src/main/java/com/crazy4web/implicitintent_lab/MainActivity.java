package com.crazy4web.implicitintent_lab;


import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdaptor;
    RecyclerView.LayoutManager layoutManager;
    Bundle bundle;



    List<String> dataitems = new ArrayList<>();

    private String url;

    static List<String> urllist = new ArrayList<>();
    LinearLayout linearLayout;
    String sharedtext;

    String sharedtextnew;
    String urldata;
    Intent intent;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Log.d("lifecycle","oncreate");


        recyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // getting the data from chrome

        intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if("text/plain".equals(type)) {

            sharedtext = intent.getStringExtra(Intent.EXTRA_TEXT);

//            Log.d("intent",""+sharedtext);

              urllist.add(sharedtext);

        }

            mAdaptor = new MyAdaptor(this, urllist);
            recyclerView.setAdapter(mAdaptor);



        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("message"));





            webView = findViewById(R.id.mywebview);



    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            urldata = intent.getStringExtra("url").toString();


            webView.getSettings().setJavaScriptEnabled(true);

            Log.d("urldata",urldata);

            webView.setWebViewClient(new WebViewClient());

            webView.loadUrl(urldata);




        }
    };




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
