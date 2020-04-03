package com.example.tournite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParticipantRegistrationActivity extends AppCompatActivity {

    Button button_register_participant, button_cancel_register_participant;
    EditText username, email_participant, password_participant;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_registration);

        button_register_participant = findViewById(R.id.button_register_participant);
        button_cancel_register_participant = findViewById(R.id.button_cancel_register_participant);
        username = findViewById(R.id.username);
        email_participant = findViewById(R.id.email_participant);
        password_participant = findViewById(R.id.password_participant);

        button_register_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //simpan kepada database
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("user_role").setValue("Participant");
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email_participant.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password_participant.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent toSuccessRegister = new Intent(ParticipantRegistrationActivity.this, SuccessRegisterActivity.class);
                startActivity(toSuccessRegister);
            }
        });

        button_cancel_register_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCancelRegistration = new Intent(ParticipantRegistrationActivity.this, GetStartedActivity.class);
                startActivity(toCancelRegistration);
            }
        });
    }

}
