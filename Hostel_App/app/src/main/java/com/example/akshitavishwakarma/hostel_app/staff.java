package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class staff extends AppCompatActivity implements View.OnClickListener {

    EditText e_staff_name, e_staff_address, e_staff_contact, e_staff_salary;
    Button btn_submit_staff, btn_cancel_staff;
    private Context mContext;
    private TextView tv_error_msg_staff;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Staff");

        // intitialising context
        mContext = this;

        e_staff_name = (EditText) findViewById(R.id.e_staff_name);
        e_staff_address = (EditText) findViewById(R.id.e_staff_address);
        e_staff_contact = (EditText) findViewById(R.id.e_staff_contact);
        e_staff_salary = (EditText) findViewById(R.id.e_staff_salary);

        tv_error_msg_staff = (TextView) findViewById(R.id.tv_error_msg_staff);

        btn_submit_staff = (Button) findViewById(R.id.btn_submit_staff);
        btn_cancel_staff = (Button) findViewById(R.id.btn_cancel_staff);

        // gettting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // setting intent data om views
            btn_submit_staff.setText("update");
            position = bundle.getInt("position");
            e_staff_name.setText(bundle.getString("staff_Name"));
            e_staff_address.setText(bundle.getString("staff_Address"));
            e_staff_contact.setText(bundle.getString("staff_Contact"));
            e_staff_salary.setText(bundle.getString("staff_Salary"));

        }


        btn_submit_staff.setOnClickListener(this);
        btn_cancel_staff.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_submit_staff:

                // getting data from views
                String staff_Name = e_staff_name.getText().toString().trim();
                String staff_Address = e_staff_address.getText().toString().trim();
                String staff_Contact = e_staff_contact.getText().toString().trim();
                String staff_Salary = e_staff_salary.getText().toString().trim();

                tv_error_msg_staff.setVisibility(View.VISIBLE);

                if (staff_Name.length() == 0) {
                    tv_error_msg_staff.setText("Please enter staff name");
                } else if (staff_Address.length() == 0) {
                    tv_error_msg_staff.setText("Please enter staff address");
                } else if (staff_Contact.length() == 0) {
                    tv_error_msg_staff.setText("Please enter the staff contact");
                } else if (staff_Salary.length() == 0) {
                    tv_error_msg_staff.setText("Please enter the staff salary");
                } else {

                    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);

                    if (btn_submit_staff.getText().toString().equalsIgnoreCase("update")) {
                        // updating contact into DB
                        databaseHelper.updatestaff(position, staff_Name, staff_Address, staff_Contact, staff_Salary);
                        Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // adding new contact into DB

                        databaseHelper.addstaff(staff_Name, staff_Address, staff_Contact, staff_Salary);
                        Toast.makeText(this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(mContext, ManageStaffDetail.class));
                    finish();
                }


                break;

            case R.id.btn_cancel_staff:
                // cancel button clicked
                startActivity(new Intent(mContext, ManageStaffDetail.class));
                finish();
                break;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return true;
        }
    }
}


