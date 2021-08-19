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

public class student extends AppCompatActivity implements View.OnClickListener {

    EditText e_name, e_dob, e_address, e_scontact, e_email, e_jd, e_cn, e_csd, e_sfn, e_sfo, e_pa, e_pcontact;
    Button b_submit, b_cancel;
    private Context mContext;
    private TextView tv_error_message;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Student");


        // intitialising context
        mContext = this;

        e_name = (EditText) findViewById(R.id.e1);
        e_dob = (EditText) findViewById(R.id.e2);
        e_address = (EditText) findViewById(R.id.e3);
        e_scontact = (EditText) findViewById(R.id.e4);
        e_email = (EditText) findViewById(R.id.e5);
        e_jd = (EditText) findViewById(R.id.e6);
        e_cn = (EditText) findViewById(R.id.e7);
        e_csd = (EditText) findViewById(R.id.e8);
        e_sfn = (EditText) findViewById(R.id.e9);
        e_sfo = (EditText) findViewById(R.id.e10);
        e_pa = (EditText) findViewById(R.id.e11);
        e_pcontact = (EditText) findViewById(R.id.e12);
        tv_error_message = (TextView) findViewById(R.id.tv_error_message);

        b_submit = (Button) findViewById(R.id.b_submit_stu_detil);
        b_cancel = (Button) findViewById(R.id.b_cancel_stu_detail);

        // gettting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // setting intent data om views
            b_submit.setText("update");
            position = bundle.getInt("position");
            e_name.setText(bundle.getString("studentName"));
            e_dob.setText(bundle.getString("studentDob"));
            e_address.setText(bundle.getString("studentAddress"));
            e_scontact.setText(bundle.getString("studentContact"));
            e_email.setText(bundle.getString("studentEmail"));
            e_jd.setText(bundle.getString("studentJd"));
            e_cn.setText(bundle.getString("studentCn"));
            e_csd.setText(bundle.getString("studentCsd"));
            e_sfn.setText(bundle.getString("studentSfn"));
            e_sfo.setText(bundle.getString("studentSfo"));
            e_pa.setText(bundle.getString("studentPa"));
            e_pcontact.setText(bundle.getString("studentContactnumber"));
        }


        b_submit.setOnClickListener(this);
        b_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.b_submit_stu_detil:

                // getting data from views
                String studentName = e_name.getText().toString().trim();
                String studentDob = e_dob.getText().toString().trim();
                String studentAddress = e_address.getText().toString().trim();
                String studentContactnumber = e_scontact.getText().toString().trim();
                String studentEmailid = e_email.getText().toString().trim();
                String studentJd = e_jd.getText().toString().trim();
                String studentCollegename = e_cn.getText().toString().trim();
                String studentCurrentstudydetails = e_csd.getText().toString().trim();
                String studentFname = e_sfn.getText().toString().trim();
                String studentFoccupation = e_sfo.getText().toString().trim();
                String studentPaddress = e_pa.getText().toString().trim();
                String studentPcontactnumber = e_pcontact.getText().toString().trim();

                tv_error_message.setVisibility(View.VISIBLE);
                if (studentName.length() == 0) {
                    tv_error_message.setText("Please enter name.");
                } else if (studentDob.length() == 0) {
                    tv_error_message.setText("Please enter DOB.");
                } else if (studentAddress.length() == 0) {
                    tv_error_message.setText("Please enter the  address.");
                } else if (studentContactnumber.length() == 0) {
                    tv_error_message.setText("Please enter the student contact no.");
                } else if (studentEmailid.length() == 0) {
                    tv_error_message.setText("Please enter Email adress.");
                } else if (studentJd.length() == 0) {
                    tv_error_message.setText("Please enter Joining Date.");
                } else if (studentCollegename.length() == 0) {
                    tv_error_message.setText("Please enter College Name.");
                } else if (studentCurrentstudydetails.length() == 0) {
                    tv_error_message.setText("Please enter Course.");
                } else if (studentFname.length() == 0) {
                    tv_error_message.setText("Please enter Father Name.");
                } else if (studentFoccupation.length() == 0) {
                    tv_error_message.setText("Please enter Father Occupation ");
                } else if (studentPaddress.length() == 0) {
                    tv_error_message.setText("Please enter Permanent Address.");
                } else if (studentPcontactnumber.length() == 0) {
                    tv_error_message.setText("Please enter Parent Contact Number");
                } else {

                    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);

                    if (b_submit.getText().toString().equalsIgnoreCase("update")) {
                        // updating contact into DB
                        databaseHelper.updateStudent(position,studentName, studentDob, studentAddress, studentContactnumber, studentEmailid, studentJd, studentCollegename, studentCurrentstudydetails, studentFname, studentFoccupation, studentPaddress, studentPcontactnumber);
                        Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // adding new contact into DB

                        databaseHelper.addstudent(studentName, studentDob, studentAddress, studentContactnumber, studentEmailid, studentJd, studentCollegename, studentCurrentstudydetails, studentFname, studentFoccupation, studentPaddress, studentPcontactnumber);
                        Toast.makeText(this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(mContext, ManageStudentDetail.class));
                    finish();
                }


                break;

            case R.id.b_cancel_stu_detail:
                // cancel button clicked
                startActivity(new Intent(mContext, ManageStudentDetail.class));
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
