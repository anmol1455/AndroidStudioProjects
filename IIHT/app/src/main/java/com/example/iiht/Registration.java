package com.example.iiht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabbutton);
        getSupportActionBar().setTitle("Registration");

        btncancel = (Button) findViewById(R.id.bt2);
        btncancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == btncancel){


            this.finish();
        }

    }
}
