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

public class fees extends AppCompatActivity implements View.OnClickListener {

    EditText e1, e2, e3, e4, e5, e6, e7;
    Button btn_submit_fees, btn_cancel_fees;
    private Context mContext;
    private TextView tv_error_msg;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Fees");


        // intitialising context
        mContext = this;

        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        e7 = (EditText) findViewById(R.id.e7);
        tv_error_msg = (TextView) findViewById(R.id.tv_error_msg);

        btn_submit_fees = (Button) findViewById(R.id.btn_submit_fees);
        btn_cancel_fees = (Button) findViewById(R.id.btn_cancel_fees);

        // gettting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // setting intent data om views
            btn_submit_fees.setText("update");
            position = bundle.getInt("position");
            e1.setText(bundle.getString("submit_Date"));
            e2.setText(bundle.getString("stu_Name"));
            e3.setText(bundle.getString("fees_Amount"));
            e4.setText(bundle.getString("received_Fees"));
            e5.setText(bundle.getString("due_Fees"));
            e6.setText(bundle.getString("receipt_Number"));
            e7.setText(bundle.getString("received_By"));
        }


        btn_submit_fees.setOnClickListener(this);
        btn_cancel_fees.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_submit_fees:

                // getting data from views
                String submit_Date = e1.getText().toString().trim();
                String stu_Name = e2.getText().toString().trim();
                String fees_Amount = e3.getText().toString().trim();
                String received_Fees = e4.getText().toString().trim();
                String due_Fees = e5.getText().toString().trim();
                String receipt_Number = e6.getText().toString().trim();
                String received_By = e7.getText().toString().trim();

                tv_error_msg.setVisibility(View.VISIBLE);

                if (submit_Date.length() == 0) {
                    tv_error_msg.setText("Please enter date.");
                } else if (stu_Name.length() == 0) {
                    tv_error_msg.setText("Please enter student name");
                } else if (fees_Amount.length() == 0) {
                    tv_error_msg.setText("Please enter the fee amount");
                } else if (received_Fees.length() == 0) {
                    tv_error_msg.setText("Please enter the received fee");
                } else if (due_Fees.length() == 0) {
                    tv_error_msg.setText("Please enter due fees");
                } else if (receipt_Number.length() == 0) {
                    tv_error_msg.setText("Please enter receipt number");
                } else if (received_By.length() == 0) {
                    tv_error_msg.setText("Please enter received by");

                } else {

                    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);

                    if (btn_submit_fees.getText().toString().equalsIgnoreCase("update")) {
                        // updating contact into DB
                        databaseHelper.updatefees(position, submit_Date, stu_Name, fees_Amount, received_Fees, due_Fees, receipt_Number, received_By);
                        Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // adding new contact into DB

                        databaseHelper.addfees(submit_Date, stu_Name, fees_Amount, received_Fees, due_Fees, receipt_Number, received_By);
                        Toast.makeText(this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(mContext, ManageFeesDetail.class));
                    finish();
                }


                break;

            case R.id.btn_cancel_fees:
                // cancel button clicked
                startActivity(new Intent(mContext, ManageFeesDetail.class));
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
