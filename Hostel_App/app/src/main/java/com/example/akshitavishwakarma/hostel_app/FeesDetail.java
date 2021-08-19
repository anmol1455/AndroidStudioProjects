package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class FeesDetail extends AppCompatActivity {
    private Context mContext;
    private TextView tv_letter,tv_stu_Id,tv_submit_Date,tv_stu_Name,tv_fess_Amount,tv_received_Fees,tv_due_Fees,tv_receipt_Number,tv_received_By;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Fees Detail");

        // initalising context
        mContext = this;

        // finding views
        tv_letter = (TextView) findViewById(R.id.tv_letter);
        tv_stu_Id = (TextView) findViewById(R.id.tv_stu_id);
        tv_submit_Date = (TextView) findViewById(R.id.tv_submit_date);
        tv_stu_Name = (TextView) findViewById(R.id.tv_stu_name);
        tv_fess_Amount = (TextView) findViewById(R.id.tv_fees_amount);
        tv_received_Fees = (TextView) findViewById(R.id.tv_rev_fees);
        tv_due_Fees = (TextView) findViewById(R.id.tv_due_fees);
        tv_receipt_Number = (TextView) findViewById(R.id.tv_rcp_number);
        tv_received_By = (TextView) findViewById(R.id.tv_rcv_by);


        // getting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String letter = "" + bundle.getString("stu_Name").charAt(0);

            // setting intent data on views

            tv_letter.setText(letter);
            tv_submit_Date.setText("Submit_Date : " + bundle.getString("submit_Date"));
            tv_stu_Name.setText("Student_Name : " + bundle.getString("stu_Name"));
            tv_fess_Amount.setText("Fees_Amount : " + bundle.getString("fees_Amount"));
            tv_received_Fees.setText("Recevied_Fess : " + bundle.getString("received_Fees"));
            tv_due_Fees.setText("Due_Fees : " + bundle.getString("due_Fees"));
            tv_receipt_Number.setText("Receipt_Number : " + bundle.getString("receipt_Number"));
            tv_received_By.setText("Recieved_By : " + bundle.getString("received_By"));

        } }
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

