package com.example.iiht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setTitle("Gallery");
    }
}
