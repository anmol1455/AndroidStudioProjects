package com.example.calling;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.telephony.SmsManager;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button userid, reset ,msgbtnn,emaill;
    EditText eduser, edpass;
    Context mcontext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eduser = (EditText) findViewById(R.id.user);
        edpass = (EditText) findViewById(R.id.pass);
        userid = (Button) findViewById(R.id.login);
        reset = (Button) findViewById(R.id.reset);
        msgbtnn=(Button)findViewById(R.id.sndmsgg);
        emaill=(Button)findViewById(R.id.startmail);


        userid.setOnClickListener(this);
        reset.setOnClickListener(this);
        msgbtnn.setOnClickListener(this);
        emaill.setOnClickListener(this);

        this.mcontext = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:8542094224"));
                //  if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                // {
                //return;
                //  }
                startActivity(call);


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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String v1 = eduser.getText().toString();
        String v2 = edpass.getText().toString();


        if (view == userid) {
            if (eduser.length() == 0) {
                Toast.makeText(this, String.valueOf("Field can't be blank"), Toast.LENGTH_LONG).show();

            }
            if (edpass.length() == 0) {
                Toast.makeText(this, String.valueOf("Field can't be blank"), Toast.LENGTH_LONG).show();
            }
            if (v1.equals("Admin") && v2.equals("Anmol")) {

                Toast.makeText(this, String.valueOf("Login Successful"), Toast.LENGTH_LONG).show();
                Intent obj = new Intent(mcontext, Login.class);
                startActivity(obj);
            } else {
                Toast.makeText(this, String.valueOf("Invalid Crendentials"), Toast.LENGTH_LONG).show();
            }
        }
        if (view == reset) {

            eduser.setText("");
            edpass.setText("");
        }

        if(view==msgbtnn)
        {
            Intent obj5=new Intent(this,Message.class);
            startActivity(obj5);
        }
        if(view==emaill)
        {
            Intent obj6=new Intent(this,Mail.class);
            startActivity(obj6);
        }
    }

}
