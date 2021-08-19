package com.example.drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5000);

                    Intent intent11 = new Intent(getBaseContext(), MainActivity.class);
                  startActivity(intent11);


                   finish();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        };
        thread.start();
    }
}
