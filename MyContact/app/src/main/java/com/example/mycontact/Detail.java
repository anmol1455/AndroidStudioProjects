package com.example.mycontact;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity implements View.OnClickListener {

    //    TextView text, text1, text2, text3, text4;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button btn1, btn2, btn3;
    //String name, phone, email, organisation, address, address1;
    Context mcontext;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Details");

//        text = (TextView) findViewById(R.id.textView);
//        text1 = (TextView) findViewById(R.id.textView1);
//        text2 = (TextView) findViewById(R.id.textView2);
//        text3 = (TextView) findViewById(R.id.textView3);
//        text4 = (TextView) findViewById(R.id.textView4);

        this.mcontext = this;

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText1);
        ed3 = (EditText) findViewById(R.id.editText2);
        ed4 = (EditText) findViewById(R.id.editText3);
        ed5 = (EditText) findViewById(R.id.editText4);
        ed6 = (EditText) findViewById(R.id.editText5);

        btn1 = (Button) findViewById(R.id.bt1);
        btn2 = (Button) findViewById(R.id.bt2);
        btn3 = (Button) findViewById(R.id.bt3);

        //  Bundle bundle = getIntent().getExtras();

//        if (bundle != null) {
//
//            btn1.setText("Update");
//            position = bundle.getInt("position");
//            ed1.setText(bundle.getString("userName"));
//            ed2.setText(bundle.getString("userPhone"));
//            ed3.setText(bundle.getString("userEmail"));
//            ed4.setText(bundle.getString("userOrg"));
//            ed5.setText(bundle.getString("Address"));
//            ed6.setText(bundle.getString("Address1"));
//        }


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == btn1) {

//
//            AlertDialog.Builder demo = new AlertDialog.Builder(this);
//            demo.setTitle("Register");
//            demo.setMessage("Sure to register??");
//            demo.setCancelable(false);
//
//            demo.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
            String name = ed1.getText().toString();
            String phone = ed2.getText().toString();
            String email = ed3.getText().toString();
            String organisation = ed4.getText().toString();
            String address = ed5.getText().toString();
            String address1 = ed6.getText().toString();


//            Intent intent = new Intent(Detail.this, MainActivity.class);
//            intent.putExtra("Name", "Name:" + name);
//            intent.putExtra("Phone", "Phone No:" + phone);
//            intent.putExtra("Email", "Email:" + email);
//            intent.putExtra("Organisation", "Organisation:" + organisation);
//            intent.putExtra("Address", "Address:" + address);
//            intent.putExtra("Address1", address1);
            if (name.length() == 0) {
                Toast.makeText(mcontext, "can't leave Name field blank..", Toast.LENGTH_LONG).show();
            } else if (phone.length() == 0) {
                Toast.makeText(mcontext, "can't leave Phone field blank..", Toast.LENGTH_LONG).show();
            } else if (organisation.length() == 0) {
                Toast.makeText(mcontext, "can't leave Organisation field blank..", Toast.LENGTH_LONG).show();
            } else if (address.length() == 0) {
                Toast.makeText(mcontext, "can't leave Address field blank..", Toast.LENGTH_LONG).show();
            } else if (email.length() == 0) {
                Toast.makeText(mcontext, "can't Email leave field blank..", Toast.LENGTH_LONG).show();
            } else {
                DatabaseHelper databaseHelper = new DatabaseHelper(mcontext);
//                if(btn1.getText().toString().equalsIgnoreCase("Update")){
//                    databaseHelper.updateContact(position,name,phone,email,organisation,address,address1 );
//                    Toast.makeText(getApplicationContext(),"Record Updated Successfully",Toast.LENGTH_LONG).show();
//                }else{
                databaseHelper.addContact(name, phone, email, organisation, address, address1);
                Toast.makeText(mcontext, "Record Added Successfully", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(mcontext, MainActivity.class));
            }
                finish();
        }
//            startActivity(intent);
//

//        }
//            });
//            demo.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.cancel();
//                }
//            });
//            AlertDialog alert = demo.create();
//            alert.show();
//        }

        if (view == btn2) {

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");

        }
        if (view == btn3) {
            Toast.makeText(getApplicationContext(), "Operation Cancelled", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }

}
