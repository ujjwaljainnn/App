package com.example.employeeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    void logIn() {
        if (MainActivity.isEmployee == true) {
            Intent intent = new Intent(getApplicationContext(), CurrentStatusEmployee.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), CurrentlyCheckedInList.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("INFO", "Logging In");
                            logIn();

                        } else {
                            //Sign up the user
                            mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (MainActivity.isEmployee==true) {
                                            FirebaseDatabase.getInstance().getReference().child("employees").child(task.getResult().getUser().getUid()).child("employeeEmail").setValue(emailEditText.getText().toString());
                                            //Need to ask the user the email id of the employer
                                            Intent intent = new Intent(getApplicationContext(), WhoIsEmployerActivity.class);
                                            startActivity(intent);

                                        } else {
                                            FirebaseDatabase.getInstance().getReference().child("employers").child(task.getResult().getUser().getUid()).child("employerEmail").setValue(emailEditText.getText().toString());
                                            Intent intent = new Intent(getApplicationContext(), CurrentlyCheckedInList.class );
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Failed. Try Again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}


