package com.example.datapassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit1,edit2;
    Button s,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1=(EditText)findViewById(R.id.ed1);
        edit2=(EditText)findViewById(R.id.ed2);

        s=(Button)findViewById(R.id.save);
        c=(Button)findViewById(R.id.cancel);

        c.setOnClickListener(this);
        s.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name=edit1.getText().toString();
        String add=edit2.getText().toString();

        Intent intent=new Intent(MainActivity.this,call.class);
                intent.putExtra("name",name);
                intent.putExtra("add",add);
                startActivity(intent);

    }
}
