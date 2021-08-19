package com.example.akshitavishwakarma.hostel_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(splash.this,home.class);
                startActivity(i);

                finish();

            }

        },SPLASH_TIME_OUT);
    }
}
