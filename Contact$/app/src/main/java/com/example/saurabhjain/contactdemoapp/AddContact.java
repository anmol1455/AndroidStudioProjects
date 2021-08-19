package com.example.saurabhjain.contactdemoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private EditText et_UserName, et_UserMobile, et_UserEmail, et_UserDOB;
    private Button btn_Save, btn_Cancel;
    private TextView tv_error_message;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // intitialising context
        mContext = this;

        // finding views
        et_UserName = (EditText) findViewById(R.id.et_user_name);
        et_UserMobile = (EditText) findViewById(R.id.et_user_mobile);
        et_UserEmail = (EditText) findViewById(R.id.et_user_email);
        et_UserDOB = (EditText) findViewById(R.id.et_user_dob);
        tv_error_message = (TextView) findViewById(R.id.tv_error_message);

        btn_Save = (Button) findViewById(R.id.btn_save);
        btn_Cancel = (Button) findViewById(R.id.btn_cancel);

        // gettting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // setting intent data om views
            btn_Save.setText(R.string.update);
            position = bundle.getInt("position");
            et_UserName.setText(bundle.getString("userName"));
            et_UserMobile.setText(bundle.getString("userPhone"));
            et_UserEmail.setText(bundle.getString("userEmail"));
            et_UserDOB.setText(bundle.getString("userDOB"));
        }

        // setting listerner on button
        btn_Save.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.btn_save:

                // getting data from views
                String userName = et_UserName.getText().toString().trim();
                String userMobile = et_UserMobile.getText().toString().trim();
                String userEmail = et_UserEmail.getText().toString().trim();
                String userDob = et_UserDOB.getText().toString().trim();

                tv_error_message.setVisibility(View.VISIBLE);
                if (userName.length() == 0) {
                    tv_error_message.setText("Please enter name.");
                } else if (userMobile.length() == 0) {
                    tv_error_message.setText("Please enter mobile number.");
                } else if (userEmail.length() == 0) {
                    tv_error_message.setText("Please enter email address.");
                } else if (userDob.length() == 0) {
                    tv_error_message.setText("Please enter dob.");
                } else {

                    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);

                    if (btn_Save.getText().toString().equalsIgnoreCase("update")) {
                        // updating contact into DB
                        databaseHelper.updateContact(position, userName, userEmail, userMobile, userDob);
                        Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // adding new contact into DB
                        databaseHelper.addContact(userName, userMobile, userEmail, userDob);
                        Toast.makeText(this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                }

                break;

            case R.id.btn_cancel:
                // cancel button clicked
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
    }
}
