package com.example.calling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity implements View.OnClickListener {

    EditText   textphone, textmsg;
    String phoneno, msg;
Button sendBtn;
    Context mcontext;
    private final static int MY_PERMISSION_REQUEST_SEND_SMS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().setTitle("Message");
        textphone = (EditText) findViewById(R.id.editText);
        textmsg = (EditText) findViewById(R.id.editText1);
        sendBtn = (Button) findViewById(R.id.btnSendSMS);

        sendBtn.setOnClickListener(this);



    }


        protected void sndsmsmsgg() {
            phoneno = textphone.getText().toString();
            msg = textmsg.getText().toString();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSION_REQUEST_SEND_SMS);
                }
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSION_REQUEST_SEND_SMS:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneno, null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "SMS Not Sent", Toast.LENGTH_LONG).show();
                        return;
                    }

            }
        }

    @Override
    public void onClick(View view) {

if(view==sendBtn){

        sndsmsmsgg();
    }



}
    }


