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
import com.google.firebase.database.ValueEventListener;;

public class TourOperatorRegistrationActivity extends AppCompatActivity {

    Button button_register_operator, button_cancel_register_operator;
    EditText username, company_name, email_operator, password_operator;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_operator_registration);

        button_register_operator = findViewById(R.id.button_register_operator);
        button_cancel_register_operator = findViewById(R.id.button_cancel_register_operator);
        username = findViewById(R.id.username);
        company_name = findViewById(R.id.company_name);
        email_operator = findViewById(R.id.email_operator);
        password_operator = findViewById(R.id.password_operator);

        button_register_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //simpan kepada database
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("user_role").setValue("Tour Operator");
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("company_name").setValue(company_name.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email_operator.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password_operator.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent toSuccessRegister = new Intent(TourOperatorRegistrationActivity.this, SuccessRegisterActivity.class);
                startActivity(toSuccessRegister);
            }
        });


        button_cancel_register_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCancelRegisttration = new Intent(TourOperatorRegistrationActivity.this, GetStartedActivity.class);
                startActivity(toCancelRegisttration);
            }
        });
    }
}
