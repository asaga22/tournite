package com.example.tournite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView ellipse1, ellipse2, ellipse3;
    TextView app_name;
    Animation ttb, ftr, ftl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ellipse1 = findViewById(R.id.ellipse1);
        ellipse2 = findViewById(R.id.ellipse2);
        ellipse3 = findViewById(R.id.ellipse3);
        app_name = findViewById(R.id.app_name);

        //load animation

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        ftr = AnimationUtils.loadAnimation(this, R.anim.ftr);
        ftl = AnimationUtils.loadAnimation(this, R.anim.ftl);

        //run animation
        ellipse1.startAnimation(ttb);
        ellipse2.startAnimation(ftr);
        ellipse3.startAnimation(ftl);
        app_name.startAnimation(ttb);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toGetStarted = new Intent(SplashActivity.this, GetStartedActivity.class);
                startActivity(toGetStarted);
                finish();
            }
        }, 2000);
    }
}
