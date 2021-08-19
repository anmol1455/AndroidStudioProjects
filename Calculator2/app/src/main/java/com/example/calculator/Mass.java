package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mass extends AppCompatActivity implements View.OnClickListener {
Button convertor;
EditText text;
TextView tv;
float lk,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);
convertor=(Button) findViewById(R.id.massbtn);
text= (EditText) findViewById(R.id.mass);
tv=(TextView) findViewById(R.id.massr);
convertor.setOnClickListener(this);
getSupportActionBar().setTitle("Mass Converter");
    }

    @Override
    public void onClick(View view) {
        String v1=text.getText().toString();
        lk=Integer.parseInt(v1);
        if(view==convertor)
        {
            result=lk*1000;
            tv.setText(Integer.toString((int) result));
            Toast.makeText(this,String.valueOf(result),Toast.LENGTH_LONG).show();
        }
    }
}

