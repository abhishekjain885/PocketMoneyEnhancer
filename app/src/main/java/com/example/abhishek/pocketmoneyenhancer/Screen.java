package com.example.abhishek.pocketmoneyenhancer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;


public class Screen extends Activity {
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_screen);

        final SharedPreferences mSharedPreference= getSharedPreferences("M",0);

        setContentView(R.layout.activity_screen);
        TextView tv = (TextView) findViewById(R.id.text);



        // TextView tv = (TextView) findViewById(R.id.appname);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "Chunkfive.otf");
        tv.setTypeface(face);


        callbackManager = CallbackManager.Factory.create();
        LoginButton button = (LoginButton) findViewById(R.id.fb_login_button);
        Typeface font = Typeface.createFromAsset(getAssets(), "GoodDog.otf");
        button.setTypeface(font);
        button.setReadPermissions(Arrays.asList("user_friends"));
        final Activity a  = this;
        String g=button.getText().toString();
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fb,0,0,0);

        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("facebooklog", AccessToken.getCurrentAccessToken().getToken());
                Toast.makeText(a, Profile.getCurrentProfile().getName(), Toast.LENGTH_LONG).show();
                SharedPreferences NAME = getSharedPreferences("NAME", 0);
                SharedPreferences.Editor editor;
                editor = NAME.edit();
                editor.putString("NAME", Profile.getCurrentProfile().getName());//0 signifies not logged in
                editor.commit();
                SharedPreferences.Editor editor1;
                editor1 = mSharedPreference.edit();
                editor1.putInt("key",1);//1 signifies logged in
                editor1.commit();
                Intent changeActivity = new Intent(a,UserDisplayActivity.class);
                //changeActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(changeActivity);

            }

            @Override
            public void onCancel() {
                Toast.makeText(a, "Cancelled" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(a, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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
