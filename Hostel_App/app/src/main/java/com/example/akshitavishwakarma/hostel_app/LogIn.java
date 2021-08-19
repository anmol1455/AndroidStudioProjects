package com.example.akshitavishwakarma.hostel_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    Button b_student_detail, b_staff_detail, b_fee_detail, bt_my_profile;
    Context mContext;
    ImageView iv_logout;
    TextView tv1;
    AlertDialog builder;
    String lid, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().setTitle("Manage Details");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_logout);
        this.mContext = this;

        iv_logout = (ImageView) getSupportActionBar().getCustomView().findViewById(R.id.iv_logout);
        iv_logout.setOnClickListener(this);

        b_student_detail = (Button) findViewById(R.id.b_student_detail);
        b_staff_detail = (Button) findViewById(R.id.b_staff_detail);
        b_fee_detail = (Button) findViewById(R.id.b_fee_detail);
        bt_my_profile = (Button) findViewById(R.id.bt_my_profile);

        b_student_detail.setOnClickListener(this);
        b_staff_detail.setOnClickListener(this);
        b_fee_detail.setOnClickListener(this);
        bt_my_profile.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle Ex = getIntent().getExtras();
        lid = Ex.getString("LID");
        pwd = Ex.getString("PD");

    }

    @Override
    public void onClick(View v) {

        if (v == b_student_detail) {
            //Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ManageStudentDetail.class);
            startActivity(intent);
        }

        if (v == b_staff_detail) {
            Intent intent = new Intent(getApplicationContext(), ManageStaffDetail.class);
            startActivity(intent);

        }

        if (v == b_fee_detail) {
            Intent intent = new Intent(getApplicationContext(), ManageFeesDetail.class);
            startActivity(intent);
        }

        if (v == bt_my_profile) {
            Intent intent = new Intent(getApplicationContext(), AccountDetails.class);
            intent.putExtra("Lid", lid);
            intent.putExtra("pd", pwd);
            startActivity(intent);
        }

        if (v == iv_logout) {

            builder = new AlertDialog.Builder(mContext)

                    .setTitle("Logout")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Do You Want To logout?")

                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                        }

                    })

                    .setNegativeButton("logout", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();

//            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
//            this.finish();
        }
    }


    boolean doubleBackToExitPressed = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressed) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressed = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed = false;
            }
        }, 1000);

    }
}