package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class temperature extends AppCompatActivity implements View.OnClickListener {
    Button convertor;
    EditText text;
    TextView tv;
    float tmf, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("TEMP. converter");
        setContentView(R.layout.activity_temperature);
        convertor = (Button) findViewById(R.id.tempbtn);
        text = (EditText) findViewById(R.id.tempf);
        tv = (TextView) findViewById(R.id.tempc);
        convertor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String v1 = text.getText().toString();
        tmf = Integer.parseInt(v1);
        if (view == convertor) {
            result = (tmf - 32) * 5 / 9;
            tv.setText(Integer.toString((int) result));
            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_LONG).show();
        }
    }
}
