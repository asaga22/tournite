package com.example.tournite;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GetStartedActivity extends AppCompatActivity {

    private Button button_getstarted;
    TextView app_slogan, app_sub1, signin_getstrated;
    Animation btt, ttb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        button_getstarted = findViewById(R.id.button_getstarted);
        app_slogan = findViewById(R.id.app_slogan);
        app_sub1 = findViewById(R.id.app_sub1);
        signin_getstrated = findViewById(R.id.signin_getstarted);

        //load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        //run Animation
        app_slogan.startAnimation(ttb);
        button_getstarted.startAnimation(btt);
        app_sub1.startAnimation(btt);
        signin_getstrated.startAnimation(btt);

        TextView signin_getstarted = (TextView) findViewById(R.id.signin_getstarted);
        signin_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(GetStartedActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });


        button_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(GetStartedActivity.this);

                dialog.setContentView(R.layout.registration_option);

                Button as_touroperator_option = dialog.findViewById(R.id.as_touroperator_option);
                as_touroperator_option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toTourOperatorRegistration = new Intent( GetStartedActivity.this, TourOperatorRegistrationActivity.class);
                        startActivity(toTourOperatorRegistration);
                    }
                });

                Button as_participant_option = dialog.findViewById(R.id.as_participant_option);
                as_participant_option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toParticipantRegistration = new Intent(GetStartedActivity.this, ParticipantRegistrationActivity.class);
                        startActivity(toParticipantRegistration);
                    }
                });

                ImageView close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toGetStartedActivity = new Intent(GetStartedActivity.this, GetStartedActivity.class);
                        startActivity(toGetStartedActivity);
                    }
                });

                ImageView closebtn = dialog.findViewById(R.id.close);
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

            }
        });
    }
}
