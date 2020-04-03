package com.example.tournite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    ImageView ellipse1, ellipse2, ellipse3;
    TextView welcome;
    Animation btt, ftr, ftl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ellipse1 = findViewById(R.id.ellipse1);
        ellipse2 = findViewById(R.id.ellipse2);
        ellipse3 = findViewById(R.id.ellipse3);
        welcome = findViewById(R.id.welcome);

        //load animation

        btt = AnimationUtils.loadAnimation(this, R.anim.ttb);
        ftr = AnimationUtils.loadAnimation(this, R.anim.ftr);
        ftl = AnimationUtils.loadAnimation(this, R.anim.ftl);

        //run animation
        ellipse1.startAnimation(btt);
        ellipse2.startAnimation(ftr);
        ellipse3.startAnimation(ftl);
        welcome.startAnimation(btt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toHome = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(toHome);
                finish();
            }
        }, 2000);
    }
}
