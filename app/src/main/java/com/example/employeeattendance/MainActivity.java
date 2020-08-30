package com.example.employeeattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarTrackingOptions;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarUser;

public class MainActivity extends AppCompatActivity {

    static boolean isEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button employeeButton = findViewById(R.id.employeeButton);
        Button employerButton = findViewById(R.id.employerButton);

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                isEmployee = true;
            }
        });

        employerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                isEmployee = false;
            }
        });

        // request permissions
        if (Build.VERSION.SDK_INT >= 23) {
            int requestCode = 0;
            if (Build.VERSION.SDK_INT >= 29) {
                ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, requestCode);
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
            }
        }


        Radar.initialize(this, "prj_live_pk_7701961878c51c9b95cbfd5b11775ec4c8675ef4");

        //foreground tracking
        /*Radar.trackOnce(new Radar.RadarTrackCallback() {
            @Override
            public void onComplete(@NotNull Radar.RadarStatus radarStatus, @Nullable Location location, @Nullable RadarEvent[] radarEvents, @Nullable RadarUser radarUser) {
                //do something with radarStatus, location, radarEvents and radarUser
                Log.i("Foreground Radar Status", radarStatus.toString());
                Log.i("Foreground Location", location.toString());
                Log.i("Foreground Radar Events", radarEvents.toString());
                Log.i("Foreground Radar User", radarUser.toString());
            }
        });*/

        //continuous background tracking
        Radar.startTracking(RadarTrackingOptions.EFFICIENT);


    }
}
