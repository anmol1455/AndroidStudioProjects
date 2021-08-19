
package com.example.saurabhjain.contactdemoapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {

    private Context mContext;
    private TextView tv_Letter, tv_UserName, tv_UserMobile, tv_UserEmail, tv_UserDoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        // initalising context
        mContext = this;

        // finding views
        tv_Letter = (TextView) findViewById(R.id.tv_letter);
        tv_UserName = (TextView) findViewById(R.id.tv_user_name);
        tv_UserEmail = (TextView) findViewById(R.id.tv_user_email);
        tv_UserMobile = (TextView) findViewById(R.id.tv_user_mobile);
        tv_UserDoB = (TextView) findViewById(R.id.tv_user_dob);

        // getting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String letter = "" + bundle.getString("userName").charAt(0);

            // setting intent data on views
            tv_Letter.setText(letter);
            tv_UserName.setText("Name : " + bundle.getString("userName"));
            tv_UserEmail.setText("Email : " + bundle.getString("userEmail"));
            tv_UserMobile.setText("Mobile : " + bundle.getString("userPhone"));
            tv_UserDoB.setText("DOB : " + bundle.getString("userDOB"));
        }
    }
}