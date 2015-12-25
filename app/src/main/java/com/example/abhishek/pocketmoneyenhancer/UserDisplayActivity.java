package com.example.abhishek.pocketmoneyenhancer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.skyfishjy.library.RippleBackground;


public class UserDisplayActivity extends ActionBarActivity implements View.OnClickListener {
    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        TextView tv = (TextView) findViewById(R.id.tv);
        Button but1, but2, but3, but4, but5, but6;



       // TextView tv = (TextView) findViewById(R.id.appname);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "KaushanScript-Regular.otf");
        tv.setTypeface(face);
       // ActionBar actionBar = getActionBar();

        final SharedPreferences mSharedPreference= getSharedPreferences("NAME",0);
        name=mSharedPreference.getString("NAME",null);
      // CharSequence c= (CharSequence)mSharedPreference.getString("NAME",null);
       // getSupportActionBar().setTitle(c);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#3399FF\">" + name + "</font>")));
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
//        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        rippleBackground.startRippleAnimation();
        but1 = (Button) findViewById(R.id.but1); but1.setOnClickListener(this);
        but2 = (Button) findViewById(R.id.but2); but2.setOnClickListener(this);
        but3 = (Button) findViewById(R.id.but3); but3.setOnClickListener(this);
        but4 = (Button) findViewById(R.id.but4); but4.setOnClickListener(this);
        but5 = (Button) findViewById(R.id.but5); but5.setOnClickListener(this);
        but6 = (Button) findViewById(R.id.but6); but6.setOnClickListener(this);








    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Logout) {
            Intent changeActivity = new Intent(this,Screen.class);
            //changeActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            final SharedPreferences mSharedPreference= getSharedPreferences("NAME",0);
           // CharSequence c= (CharSequence)mSharedPreference.getString("NAME",null);
            SharedPreferences.Editor editor1;
            editor1 = mSharedPreference.edit();
            editor1.putString(null, null);

            final SharedPreferences mSharedPreference1= getSharedPreferences("M",0);
            // CharSequence c= (CharSequence)mSharedPreference.getString("NAME",null);
            SharedPreferences.Editor editor;
            editor = mSharedPreference1.edit();
            editor.putInt("key", 0);
            editor.commit();
            editor1.commit();


            //startActivity(changeActivity);
            this.finish();


        }
         else if(id==R.id.Profile)
        {



            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Profile");


            // set dialog message
            alertDialogBuilder

                    .setMessage(name + "\n\nNo of competitions participated-10\n\nTotal Credits-500")
                    .setCancelable(false)

                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else if(id==R.id.ContactUs)
        {



            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Contact Us");

            final SpannableString s = new SpannableString("www.facebook.com \n\npocketmoneyenhancer@gmail.com");



            Linkify.addLinks(s, Linkify.ALL);


            // set dialog message
            alertDialogBuilder

                    .setMessage(s)
                    .setCancelable(false)

                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

            ((TextView)alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());


        }
        else if(id==R.id.Reedem)
        {

            final Intent changeActivity1 = new Intent(this,Redeem.class);
            startActivity(changeActivity1);

        }






        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.but1:

                final Intent changeActivity = new Intent(this,LogoActivity.class);
                startActivity(changeActivity);


                break;

            case R.id.but2:
                final Intent changeActivity1 = new Intent(this,Tagline.class);
                startActivity(changeActivity1);

                break;

            case R.id.but3:
                final Intent changeActivity2 = new Intent(this,Selfie.class);
                startActivity(changeActivity2);


                break;

            case R.id.but4:

                final Intent changeActivity3 = new Intent(this,Writing.class);
                startActivity(changeActivity3);

                break;

            case R.id.but5:
                final Intent changeActivity4 = new Intent(this,Appidea  .class);
                startActivity(changeActivity4);

                break;

            case R.id.but6:
        }

    }
}
