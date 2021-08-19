package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class StaffDetail extends AppCompatActivity {


    TextView tv_letter_staff, tv_staff_name, tv_staff_address, tv_staff_contact, tv_staff_salary;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Staff Detail");

        // initalising context
        mContext = this;

        // finding views
        tv_letter_staff = (TextView) findViewById(R.id.tv_letter_staff);
        tv_staff_name = (TextView) findViewById(R.id.tv_staff_name);
        tv_staff_address = (TextView) findViewById(R.id.tv_staff_address);
        tv_staff_contact = (TextView) findViewById(R.id.tv_staff_contact);
        tv_staff_salary = (TextView) findViewById(R.id.tv_staff_salary);

        // getting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String letter = "" + bundle.getString("staff_Name").charAt(0);

            // setting intent data on views

            tv_letter_staff.setText(letter);
            tv_staff_name.setText("staff_Name : " + bundle.getString("staff_Name"));
            tv_staff_address.setText("staff_Address : " + bundle.getString("staff_Address"));
            tv_staff_contact.setText("staff_Contact : " + bundle.getString("staff_Contact"));
            tv_staff_salary.setText("staff_Salary : " + bundle.getString("staff_Salary"));
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