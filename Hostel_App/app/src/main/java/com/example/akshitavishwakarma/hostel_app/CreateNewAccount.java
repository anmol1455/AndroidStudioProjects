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

public class CreateNewAccount extends AppCompatActivity implements View.OnClickListener {

    Button bt_cna_signup;
    EditText et1,et2,et3,et4,et5,et6;
    private Context mContext;
    private TextView tv_error_msg_cna;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);
        et5 = (EditText)findViewById(R.id.et5);
        et6 = (EditText)findViewById(R.id.et6);

        this.mContext=this;

        tv_error_msg_cna = (TextView) findViewById(R.id.tv_error_msg_cna);



//         gettting intent data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // setting intent data om views
            bt_cna_signup.setText("update");
            position = bundle.getInt("position");
            et1.setText(bundle.getString("first_Name"));
            et2.setText(bundle.getString("last_Name"));
            et3.setText(bundle.getString("mobile_Number"));
           et4.setText(bundle.getString("user_Id"));
            et5.setText(bundle.getString("password"));
            et6.setText(bundle.getString("confirm_password"));

        }


        bt_cna_signup = (Button)findViewById(R.id.bt_cna_signup);
        bt_cna_signup.setOnClickListener(this);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Create Account");

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_cna_signup:

                // getting data from views
                String first_Name = et1.getText().toString().trim();
                String last_Name = et2.getText().toString().trim();
                String mobile_Number = et3.getText().toString().trim();
                String user_Id= et4.getText().toString().trim();
                String password= et5.getText().toString().trim();
                String confirm_Password= et6.getText().toString().trim();

                tv_error_msg_cna.setVisibility(View.VISIBLE);

                if (first_Name.length() == 0) {
                    tv_error_msg_cna.setText("Please enter first name");
                } else if (last_Name.length() == 0) {
                    tv_error_msg_cna.setText("Please enter last name");
                } else if (mobile_Number.length() == 0) {
                    tv_error_msg_cna.setText("Please enter the mobile number");
                } else if (user_Id.length() == 0) {
                    tv_error_msg_cna.setText("Please enter the user id");
                } else if (password.length() == 0) {
                    tv_error_msg_cna.setText("Please enter the password");
                } else if (password.length() != confirm_Password.length()) {
                    tv_error_msg_cna.setText("mismatch password");
                }else{

                    DatabaseHelper databaseHelper = new DatabaseHelper(mContext);

//                    if (bt_cna_signup.getText().toString().equalsIgnoreCase("update")) {
//                        // updating contact into DB
//                        databaseHelper.updatestaff(position, staff_Name, staff_Address, staff_Contact, staff_Salary);
//                        Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
//                    } else {

                        // adding new contact into DB

                        databaseHelper.createNewAccount(first_Name, last_Name, mobile_Number, user_Id, password, confirm_Password);
                        Toast.makeText(this, "Signup successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), home.class));
                    finish();
                    }

                }
        }
}
