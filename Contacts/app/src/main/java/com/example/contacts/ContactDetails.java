package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {

    private Context mContext;
    private TextView tv_Letter, tv_UserName, tv_UserMobile, tv_UserEmail, tv_org, tv_add, tv_add1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        // initalising context
        mContext = this;

        // finding views
        tv_Letter = (TextView) findViewById(R.id.tv_letter);
        tv_UserName = (TextView) findViewById(R.id.tv_user_name);
        tv_UserMobile = (TextView) findViewById(R.id.tv_user_mobile);
        tv_UserEmail = (TextView) findViewById(R.id.tv_user_email);
        tv_org = (TextView) findViewById(R.id.tv_organ);
        tv_add = (TextView) findViewById(R.id.tv_address);
        tv_add1 = (TextView) findViewById(R.id.tv_address1);

// getting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String letter = "" + bundle.getString("userName").charAt(0);

            // setting intent data on views
            tv_Letter.setText(letter);
            tv_UserName.setText("Name : " + bundle.getString("userName"));
            tv_UserMobile.setText("Mobile : " + bundle.getString("userPhone"));
            tv_UserEmail.setText("Email : " + bundle.getString("userEmail"));
            tv_org.setText("Organisation : " + bundle.getString("userOrgan"));
            tv_add.setText("Address : " + bundle.getString("userAdd"));
            tv_add1.setText("Address1 : " + bundle.getString("userAdd1"));
        }
    }
}