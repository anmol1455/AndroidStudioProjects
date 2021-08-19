package com.example.musicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.terabann);



    }

    public void stopMusic(View view) {
        mp.stop();
        mp = MediaPlayer.create(this, R.raw.terabann);


    }

    public void paueMusic(View view) {
        mp.pause();


    }

    public void playMusic(View view) {

        mp.start();

    }

    public void Vibrate(View view) {
        Vibrator vb = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vb.vibrate(3000);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void next(View view) {

        mp.stop();


            mp = MediaPlayer.create(this, R.raw.bhola);

        mp.start();





    }
}
