package com.example.employeeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class AllEmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employees);

        String employerID = getIntent().getStringExtra("employerID");
        ListView allEmployeesListView = findViewById(R.id.allEmployeesListView);

        final ArrayList<String> allEmployeesList = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allEmployeesList);

        allEmployeesListView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("employers").child(employerID).child("employees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Toast.makeText(AllEmployeesActivity.this, "HII", Toast.LENGTH_SHORT).show();
                    allEmployeesList.add(dataSnapshot.getValue(String.class));
                    adapter.notifyDataSetChanged();
                    Log.i("Info", dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ;


    }
}
