package com.example.employeeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentlyCheckedInList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_checked_in_list);

        ListView currentlyCheckedInListView = findViewById(R.id.listViewCurrentlyCheckedIn);
        final ArrayList<String> items = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        currentlyCheckedInListView.setAdapter(adapter);

        Log.i("ID", FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseDatabase.getInstance().getReference().child("employers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("employees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String employeeID = dataSnapshot.getKey();
                    final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("employees").child(employeeID);
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                if (dataSnapshot1.getKey().equals("currentlyCheckedIn") && dataSnapshot1.getValue(String.class).equals("yes")) {
                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot2 : snapshot.getChildren()) {
                                                if (dataSnapshot2.getKey().equals("name")) {
                                                    items.add(dataSnapshot2.getValue(String.class));
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                        ;

                        //items.add(FirebaseDatabase.getInstance().getReference().child("employees").child(employeeID).child("name").);
                    });



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        Button viewAllEmployeesButton = findViewById(R.id.viewAllEmployeesButton);
        Button setGeofenceButton = findViewById(R.id.setGeofenceButton);

        viewAllEmployeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllEmployeesActivity.class);
                intent.putExtra("employerID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(intent);
            }
        });

        setGeofenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
