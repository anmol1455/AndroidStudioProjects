package com.example.activity_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String tag = "Events";
    LinearLayout background;
    LinearLayout bgimg;
    Button btred, btgreen, btblue, anim, flo, nat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btred = (Button) findViewById(R.id.btnred);
        btgreen = (Button) findViewById(R.id.btngreen);
        btblue = (Button) findViewById(R.id.btnblue);
        anim = (Button) findViewById(R.id.anim);
        flo = (Button) findViewById(R.id.fl);
        nat = (Button) findViewById(R.id.na);

        background = (LinearLayout) findViewById(R.id.linear);

        btblue.setOnClickListener(this);
        btgreen.setOnClickListener(this);
        btred.setOnClickListener(this);
        anim.setOnClickListener(this);
        flo.setOnClickListener(this);
        nat.setOnClickListener(this);

        bgimg = (LinearLayout) findViewById(R.id.linear);

        Log.d(tag, "onCreate() method is called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart() method is called");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(tag, "onResume() method is called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(tag, "onPause() method is called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(tag, "onStop() method is called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(tag, "onRestart() method is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(tag, "onDestroy() method is called");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnred:
                background.setBackgroundColor(Color.RED);


                break;
            case R.id.btngreen:
                background.setBackgroundColor(Color.GREEN);


                break;
            case R.id.btnblue:
               background.setBackgroundColor(Color.BLUE);


                break;
            case R.id.anim:
                bgimg.setBackgroundResource(R.drawable.doganim);
                break;
            case R.id.fl:
                background.setBackgroundResource(R.drawable.flowers);
                break;
            case R.id.na:
              background.setBackgroundResource(R.drawable.nature);
              break;
            default:
                //Toast.makeText(this,"hhh", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
