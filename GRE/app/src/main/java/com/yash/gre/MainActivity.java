package com.yash.gre;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String data = "{\"1\":{\"name\":\"yashas\",\"meaning\":\"success\"},\"2\":{\"name\":\"keerthi\",\"meaning\":\"Glory\"},\"3\":{\"name\":\"kushal\",\"meaning\":\"Asshole\"}}";



    JSONObject json;
    TextView text;
    FrameLayout frame;
    int value = 0;
    int size =3;
    String wordInDisplay;
    String wordMeaning;
    boolean entry = false;
    public void showMeaning(View view){
         text = (TextView) findViewById(R.id.word);

        frame = (FrameLayout) findViewById(R.id.frame);

        if(value == 0) {
            try {
                Log.i("size",Integer.toString(size));
                Random rand = new Random();
                int val = rand.nextInt(size);
                Log.i("whatThe1","Inside try");
                Log.i("whatThex",data);
                //JSONArray jsonPart = new JSONArray(data);
                JSONObject jsonPart = new JSONObject(data);
                Log.i("whatThe2","after json parse");
                Log.i("whatThe22",Integer.toString(val+1));
                Log.i("whatThe6",jsonPart.getString(Integer.toString(val + 1)));
                //JSONObject jsonResult = JSONObject(jsonPart.getString("1").toString());
                String temp = jsonPart.getString(Integer.toString(val + 1));
                Log.i("whatThe66",temp);
                JSONObject jsonPart2 = new JSONObject(temp);
                Log.i("whatThe7",jsonPart2.getString("name"));
                Log.i("whatThe8",jsonPart2.getString("meaning"));
                wordInDisplay = jsonPart2.getString("name");
                wordMeaning = jsonPart2.getString("meaning");
                /*for(int i = 0;i < jsonPart.length();i++){
                    Log.i("whatThe3","Inside loop");
                    JSONObject json1 = jsonPart.getJSONObject(i);
                    Log.i("whatThe",json1.getString("1"));

                }
                */
            } catch (JSONException e) {
                Log.i("whatThe3","except");
                e.printStackTrace();
            }
            //testing

            //testing
            text.setAlpha(0);
            text.setText(wordInDisplay);

            text.animate().alpha(1).setDuration(2000);
            if (entry){
                frame.animate().rotationXBy(180f).setDuration(2000);
                text.animate().rotationXBy(-180f).setDuration(2000);

            }
            //frame.animate().rotationXBy(180f).setDuration(2000);
            //text.animate().rotationXBy(180f).alpha(1).setDuration(2000);
            //frame.setBackgroundColor(Color.CYAN);
            //frame.animate().

            value = 1;
        }
        else{

            text.setAlpha(0);
            text.setText(wordMeaning);
            text.animate().alpha(1).setDuration(2000);
            /*if (entry) {
                text.animate().rotationXBy(-180f).alpha(1).setDuration(2000);
            }*/
            entry = true;
            value = 0;

        }
        //text.animate().alpha(1).setDuration(2000) ;
        Log.i("info","clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.yash.gre", Context.MODE_PRIVATE);
        int tempSize = sharedPreferences.getInt("size",-1);

        if(tempSize == -1){
            sharedPreferences.edit().putInt("size",3).apply();
            size = 3;
        }
        else{

            size = tempSize;
        }
        size = 3;

        /*
        try {
            json = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
