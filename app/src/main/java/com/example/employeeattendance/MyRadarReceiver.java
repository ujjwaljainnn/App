package com.example.employeeattendance;

import android.content.Context;
import android.location.Location;
import android.text.format.Time;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarReceiver;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarUser;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MyRadarReceiver extends RadarReceiver {
    @Override
    public void onClientLocationUpdated(@NotNull Context context, @NotNull Location location, boolean b, @NotNull Radar.RadarLocationSource radarLocationSource) {

    }

    @Override
    public void onError(@NotNull Context context, @NotNull Radar.RadarStatus radarStatus) {

    }

    @Override
    public void onEventsReceived(@NotNull Context context, @NotNull RadarEvent[] radarEvents, @NotNull RadarUser radarUser) {
        if (MainActivity.isEmployee==true) {
            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("employees").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            if (radarEvents[radarEvents.length-1].getType() == RadarEvent.RadarEventType.USER_ENTERED_GEOFENCE) {
                //USER ENTERED GEOFORCE

                //set date and time
                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                db.child("history").child(UUID.randomUUID().toString()).child("time").setValue(mydate);
                db.child("history").child(UUID.randomUUID().toString()).child("inOut").setValue("IN");

                //set currentlycheckedin
                db.child("currentlyCheckedIn").setValue("yes");

                //set textview
                CurrentStatusEmployee.checkedInStatusTextView.setText("You're currently checked in.");


            } else if (radarEvents[radarEvents.length - 1].getType() == RadarEvent.RadarEventType.USER_EXITED_GEOFENCE) {
                //USER EXITED GEOFORCE

                //set date and time
                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                db.child("history").child(UUID.randomUUID().toString()).child("time").setValue(mydate);
                db.child("history").child(UUID.randomUUID().toString()).child("inOut").setValue("OUT");

                //set currentlycheckedin
                db.child("currentlyCheckedIn").setValue("no");

                //set textview
                CurrentStatusEmployee.checkedInStatusTextView.setText("You're currently checked out.");


            }
        }

    }

    @Override
    public void onLocationUpdated(@NotNull Context context, @NotNull Location location, @NotNull RadarUser radarUser) {

    }

    @Override
    public void onLog(@NotNull Context context, @NotNull String s) {

    }
}
