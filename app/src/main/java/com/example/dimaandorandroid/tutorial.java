package com.example.dimaandorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_tutorial);
    }

    public void submitAnswer(View view) {
        Toast.makeText(getApplicationContext(),"Answer Submitted",Toast.LENGTH_SHORT).show();

    }

    public void getHint(View view) {
        Toast.makeText(getApplicationContext(),"Opening Google maps",Toast.LENGTH_SHORT).show();

        Uri locationUri = Uri.parse("geo:0,0?q=israel");
        // create an implicit intent that prompts android to open using one the installed apps
        // on the device. Pass what action is needed and which data to work on
        Intent intent = new Intent(Intent.ACTION_VIEW,locationUri);
        // check if there is a compatible app (activity) that can present the data above
        // getPackageManager - Returns PackageManager instance to find global package information.
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else{
            // if there is no compatible app print to logcat
            Log.d("geoLocationOpenError", "There is no app to open a google maps API location");
        }
    }

    public void gotIt(View view) {
        super.onBackPressed();
    }
}