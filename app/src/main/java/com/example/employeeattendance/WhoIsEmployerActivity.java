package com.example.employeeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WhoIsEmployerActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText employerEmailEditText;
    EditText nameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_is_employer);

        employerEmailEditText = findViewById(R.id.employerEmailIDEditText);
        nameEditText = findViewById(R.id.nameEditText);

        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add employer email and name to employee's tree
                FirebaseDatabase.getInstance().getReference().child("employees").child(mAuth.getCurrentUser().getUid()).child("employerEmail").setValue(employerEmailEditText.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("employees").child(mAuth.getCurrentUser().getUid()).child("name").setValue(nameEditText.getText().toString());

                //add employee id to employer's tree
                FirebaseDatabase.getInstance().getReference().child("employers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.child("employerEmail").getValue().equals(employerEmailEditText.getText().toString())) {
                                //Toast.makeText(WhoIsEmployerActivity.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference().child("employers").child(dataSnapshot.getKey()).child("employees").child(mAuth.getCurrentUser().getUid()).setValue(nameEditText.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Intent intent = new Intent(getApplicationContext(), CurrentStatusEmployee.class);
                startActivity(intent);
            }
        });




    }
}
