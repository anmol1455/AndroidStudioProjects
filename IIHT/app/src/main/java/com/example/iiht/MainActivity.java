package com.example.iiht;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;



public class MainActivity extends AppCompatActivity  {
    Context mcontext;
    Button btncancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mcontext=this;



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent obj1 = new Intent(mcontext, Registration.class);
                startActivity(obj1);

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
           Intent intent1=new Intent(mcontext,setting.class);
           startActivity(intent1);
        }
        if(id==R.id.AboutUs){
            Intent intent2=new Intent(mcontext,About.class);
            startActivity(intent2);
        }
        if(id==R.id.Profile)
        {
            Intent intent3=new Intent(mcontext,Profile.class);
            startActivity(intent3);
        }
        if(id==R.id.Gallery)
       {
          Intent intent4=new Intent(mcontext,Gallery.class);
            startActivity(intent4);
        }
if(id==R.id.contact){
    Intent intent4=new Intent(mcontext,Contact.class);
    startActivity(intent4);
}



        return super.onOptionsItemSelected(item);
    }



}

