package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean doubleback=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    @Override
    public void onBackPressed(){
        if(doubleback){
            super.onBackPressed();
            return;}
        this.doubleback=true;
        Toast.makeText(this,"Please click again to exit",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleback=false;

            }
        },2000);
    }
}
