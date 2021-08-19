package com.example.datapassing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class call extends AppCompatActivity {
    TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        text1=(TextView)findViewById(R.id.tv1);
        text2=(TextView)findViewById(R.id.tv2);

        Bundle bundle=getIntent().getExtras();

        text1.setText(bundle.getString("name"));
        text2.setText(bundle.getString("add"));
    }
}
