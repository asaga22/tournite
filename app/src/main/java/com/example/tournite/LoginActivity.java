package com.example.tournite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText xusername, xpassword;
    Button button_login, button_cancel;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);
        button_login = findViewById(R.id.button_login);
        button_cancel = findViewById(R.id.button_cancel);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ubah state button signin setelah ditekan
                button_login.setEnabled(false);
                button_login.setText("Loading...");

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username belum diisi!", Toast.LENGTH_SHORT).show();
                    //ubah state button signin setelah ditekan
                    button_login.setEnabled(true);
                    button_login.setText("SIGN IN");
                } else{
                    if(password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password belum diisi!", Toast.LENGTH_SHORT).show();
                        //ubah state button signin setelah ditekan
                        button_login.setEnabled(true);
                        button_login.setText("SIGN IN");
                    }else{
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                    //ambil password dari firebase
                                    String password_from_firebase = dataSnapshot.child("password").getValue().toString();

                                    //validasi password dengan password firebase
                                    if(password.equals(password_from_firebase)){

                                        //simpan username (key) ke local
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        //pindah activity
                                        Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(toHome);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password Salah!", Toast.LENGTH_SHORT).show();
                                        //ubah state button signin setelah ditekan
                                        button_login.setEnabled(true);
                                        button_login.setText("SIGN IN");
                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(), "Usesrname tidak ditemukan!", Toast.LENGTH_SHORT).show();
                                    //ubah state button signin setelah ditekan
                                    button_login.setEnabled(true);
                                    button_login.setText("SIGN IN");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCancelLogin = new Intent(LoginActivity.this, GetStartedActivity.class);
                startActivity(toCancelLogin);
            }
        });

    }
}
