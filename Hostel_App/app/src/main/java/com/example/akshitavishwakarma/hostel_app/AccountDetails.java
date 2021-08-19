package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountDetails extends AppCompatActivity implements View.OnClickListener {

    EditText tv_first_name, tv_last_name, tv_mob_number, tv_user_name, tv_password,tv_confirm_password , tv_letter_profile;
    private Context mContext;
    Button btnupdate, btncancel;
    Context mcontext;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Account Detail");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_edit);

        // initalising context
        mContext = this;

        // finding views
        tv_first_name = (EditText) findViewById(R.id.edfirst);
        tv_last_name = (EditText) findViewById(R.id.edlast);
        tv_mob_number = (EditText) findViewById(R.id.edmobile);
        tv_user_name = (EditText) findViewById(R.id.eduser);
        tv_password = (EditText) findViewById(R.id.edpass);
        tv_confirm_password = (EditText) findViewById(R.id.edconpass);
        btncancel = (Button) findViewById(R.id.btcancel);
        btnupdate = (Button) findViewById(R.id.btupdate);

        databaseHelper = new DatabaseHelper(mcontext);

        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        databaseHelper = new DatabaseHelper(mContext);
        Toast.makeText(this, extras.getString("Lid") + extras.getString("pd"), Toast.LENGTH_SHORT).show();
        Cursor c = databaseHelper.chklogin("AccountDetail", new String[]{"user_Id", "password"}, new String[]{extras.getString("Lid"), extras.getString("pd")});

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            tv_first_name.setText(c.getString(1));
            tv_last_name.setText(c.getString(2));
            tv_mob_number.setText(c.getString(3));
            tv_user_name.setText(c.getString(4));
            tv_password.setText(c.getString(5));
            tv_confirm_password.setText(c.getString(6));
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

    @Override
    public void onClick(View v)
    {
        if (v == btnupdate) {
            Bundle extras = getIntent().getExtras();
            String t1 = tv_first_name.getText().toString();
            String t2 = tv_last_name.getText().toString();
            String t3 = tv_mob_number.getText().toString();
            String t4 = tv_user_name.getText().toString();
            String t5 = tv_password.getText().toString();
            String t6 = tv_confirm_password.getText().toString();

            if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty()  || t5.isEmpty() || t6.isEmpty()  )
            {
                Toast.makeText(getApplicationContext(), "Enter All Fields", Toast.LENGTH_SHORT).show();

            } else {
                String col[] = {"first_Name","last_Name", "mobile_Number", "user_Id", "password", "confirm_Password"};
                String val[] = { t1, t2, t3, t4, t5, t6};

                int i= databaseHelper.updateData("AccountDetail", col, val, "user_Id", new String[]{extras.getString("Lid")});

                if(i>0) {
                    Toast.makeText(getApplicationContext(), "Updation Succesfully",Toast.LENGTH_SHORT).show();
                    extras.putString("Lid", t4);
                    finish();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Updation Unsuccesfully",Toast.LENGTH_SHORT).show();
                }
            }
        }


        if(v==btncancel)
        {
            this.finish();
        }

    }
}