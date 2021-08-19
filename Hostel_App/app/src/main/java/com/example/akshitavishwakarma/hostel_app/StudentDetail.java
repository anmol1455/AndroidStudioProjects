package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentDetail extends AppCompatActivity {

    private Context mContext;
    private TextView tv_letter,tv_student_id,tv_student_name,tv_dob,tv_address,tv_scontact_no,tv_email_id,tv_jd,tv_college_name,tv_course_name,tv_stu_fname,tv_stu_foccupation,tv_stu_paddress,tv_pcontact_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Student Detail");

        // initalising context
        mContext = this;

        // finding views
        tv_letter = (TextView) findViewById(R.id.tv_letter);
        tv_student_id = (TextView) findViewById(R.id.tv_student_id);
        tv_student_name = (TextView) findViewById(R.id.tv_student_name);
        tv_dob = (TextView) findViewById(R.id.tv_dob);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_scontact_no = (TextView) findViewById(R.id.tv_scontact_no);
        tv_email_id = (TextView) findViewById(R.id.tv_email_id);
        tv_jd = (TextView) findViewById(R.id.tv_jd);
        tv_college_name = (TextView) findViewById(R.id.tv_college_name);
        tv_course_name = (TextView) findViewById(R.id.tv_course_name);
        tv_stu_fname = (TextView) findViewById(R.id.tv_stu_fname);
        tv_stu_foccupation = (TextView) findViewById(R.id.tv_stu_foccupation);
        tv_stu_paddress = (TextView) findViewById(R.id.tv_stu_paddress);
        tv_pcontact_no = (TextView) findViewById(R.id.tv_pcontact_no);


        // getting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String letter = "" + bundle.getString("studentName").charAt(0);

            // setting intent data on views

            tv_letter.setText(letter);
            tv_student_name.setText("Name : " + bundle.getString("studentName"));
            tv_dob.setText("DOB : " + bundle.getString("studentDob"));
            tv_address.setText("Address : " + bundle.getString("studentAddress"));
            tv_scontact_no.setText("StudentContactNo. : " + bundle.getString("studentMobile"));
            tv_email_id.setText("Email : " + bundle.getString("studentEmail"));
            tv_jd.setText("Joiningdate : " + bundle.getString("studentJoiningdate"));
            tv_college_name.setText("CollegeName : " + bundle.getString("studentCollegename"));
            tv_course_name.setText("Course : " + bundle.getString("studentCourse"));
            tv_stu_fname.setText("FatherName : " + bundle.getString("studentFname"));
            tv_stu_foccupation.setText("FatherOccupation : " + bundle.getString("studentFoccupation"));
            tv_stu_paddress.setText("PermanentAddress : " + bundle.getString("studentPaddress"));
            tv_pcontact_no.setText("ParentsContactNo. : " + bundle.getString("studentFcontact"));
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
