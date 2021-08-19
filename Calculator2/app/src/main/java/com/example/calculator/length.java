package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class length extends AppCompatActivity implements View.OnClickListener {
    Button convertor;
    EditText text;
    TextView tv;
    float lkm,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        convertor=(Button) findViewById(R.id.lenbtn);
        text= (EditText) findViewById(R.id.len);
        tv=(TextView) findViewById(R.id.lenr);
        convertor.setOnClickListener(this);
        getSupportActionBar().setTitle("Length Converter");

    }

    @Override
    public void onClick(View view) {
        String v1=text.getText().toString();
        lkm=Integer.parseInt(v1);
        if(view==convertor)
        {
            result=lkm*1000;
            tv.setText(Integer.toString((int) result));
            Toast.makeText(this,String.valueOf(result),Toast.LENGTH_LONG).show();
        }
    }
    }

