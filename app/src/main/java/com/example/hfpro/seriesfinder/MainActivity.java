package com.example.hfpro.seriesfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String bigContent ;
    JSONArray jsonArray ;
    RecyclerView recyclerView;
    ArrayList<TvShow> arrayList ;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arrayList = new ArrayList<>() ;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Main2Activity.class);
                startActivity(intent);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view) ;
        bigContent =  ContentDownloader.getStringData("http://api.tvmaze.com/schedule?country=US&date=2016-08-09");
        AnotherThread anotherThread = new AnotherThread() ;
        anotherThread.execute(bigContent) ;
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this , arrayList);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
    }



    public class AnotherThread extends AsyncTask<String , Void , Void >
    {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... content) {

            try {
                jsonArray = new JSONArray(content[0]);
                for (int i = 0; i < jsonArray.length(); i++) {
                        TvShow tvCurrent = new TvShow();
                        JSONObject current1 = jsonArray.getJSONObject(i);
                        JSONObject current2 = current1.getJSONObject("show");
                        JSONObject current3 = current2.getJSONObject("network");
                        JSONObject current4 = null;
                        current4 = current2.getJSONObject("image");
                        JSONObject current5 = current3.getJSONObject("country");
                        JSONObject current6 = current2.getJSONObject("rating");

                        String name = current2.getString("name");
                        String type = current2.getString("type");
                        String country = current5.getString("name");
                        String rating = current6.getString("average");
                        String iconUrl = current4.getString("medium");
                        String url = current2.getString("url");

                        tvCurrent.setCountry(country);
                        tvCurrent.setGenre(type);
                        tvCurrent.setImage(iconUrl);
                        tvCurrent.setName(name);
                        tvCurrent.setRating(rating);
                        tvCurrent.setUrl(url);

                        arrayList.add(tvCurrent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null ;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.random) {
            // TO DO
        }

        return super.onOptionsItemSelected(item);
    }
}
