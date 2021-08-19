package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add,subs,multy,divi;
    EditText ed1, ed2;
    TextView tvresult;
    int num1,num2,ans;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed1 = (EditText) findViewById(R.id.edit1);
        ed2= (EditText) findViewById(R.id.edit2);
        tvresult=(TextView) findViewById(R.id.result);
        add= (Button) findViewById(R.id.sum);
        subs=(Button) findViewById(R.id.sub);
        multy=(Button) findViewById(R.id.mult);
        divi=(Button) findViewById(R.id.div);
        add.setOnClickListener(this);
        subs.setOnClickListener(this);
        multy.setOnClickListener(this);
        divi.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mcontext=this;


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.mass)
        {
            Intent obj1=new Intent(mcontext,Mass.class);
            startActivity(obj1);
        }
        if(id==R.id.length)
        {
            Intent obj2=new Intent(mcontext,length.class);
            startActivity(obj2);
        }
        if(id==R.id.Temperature)
        {
            Intent obj3=new Intent(mcontext,temperature.class);
            startActivity(obj3);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String v1=ed1.getText().toString();
        String v2=ed2.getText().toString();
        num1=Integer.parseInt(v1);
        num2=Integer.parseInt(v2);
        if(view==add)
        {
            ans=num1+num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==subs)
        {
            ans=num1-num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==multy)
        {
            ans=num1*num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==divi) {
            try {


                ans = num1 / num2;
                tvresult.setText(Integer.toString(ans));
                Toast.makeText(this, String.valueOf(ans), Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}



