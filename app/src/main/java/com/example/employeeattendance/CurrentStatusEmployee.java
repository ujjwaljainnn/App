package com.example.employeeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CurrentStatusEmployee extends AppCompatActivity {

    static TextView checkedInStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status_employee);

        Button pastHistoryButton = findViewById(R.id.pastHistoryButton);
        pastHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PastHistoryActivity.class);
                intent.putExtra("employeeID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(intent);
            }
        });

        checkedInStatusTextView = findViewById(R.id.checkedInStatusTextView);
    }
}
