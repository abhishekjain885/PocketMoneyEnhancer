package com.example.abhishek.pocketmoneyenhancer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;


public class MainActivity extends Activity {


    SharedPreferences isloggedin;
   // isloggedin = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor;
    public int WAIT_TIME_SPLASH_SCREEN = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent changeActivity = new Intent(this,Screen.class);
       //changeActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        final Intent changeActivity1 = new Intent(this,UserDisplayActivity.class);
       //changeActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        isloggedin=getSharedPreferences("M",0);
        editor = isloggedin.edit();

        if(isloggedin.getInt("key",-1)!=1) {
            editor.putInt("key", 0);//0 signifies not logged in

            editor.commit();
        }
        //creating thread for waiting WAIT_TIME seconds
        Thread wait = new Thread(){
            @Override
            public void run() {
                //use try catch block to avoid unhandled thread interrrupted exception and preventing app from crashing
                try {

                    sleep(WAIT_TIME_SPLASH_SCREEN);
                } catch (InterruptedException e) {
                    //Log.e("Splash Screen to Home Thread wait error",e.getMessage());
                } catch (Exception e) {
                    //Log.e("Other thread exception",e.getMessage());
                } finally{
                    //starting the activity using the intent
                    if(isloggedin.getInt("key",-1)==0) {
                        MainActivity.this.overridePendingTransition(R.anim.slide2, R.anim.slide1);
                        startActivity(changeActivity);

                    }
                    else {
                        MainActivity.this.overridePendingTransition(R.anim.slide2, R.anim.slide1);
                        startActivity(changeActivity1);

                    }

                }

            }
        };


        //creating intent for communicating with the SyncService class



        //starting the thread
        wait.start();
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
