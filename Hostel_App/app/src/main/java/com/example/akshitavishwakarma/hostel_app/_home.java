package com.example.akshitavishwakarma.hostel_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class _home extends Fragment implements View.OnClickListener {

    Button bt_log, bt_create;
    ImageButton insta,google,fb;
    String users, password;
    EditText ed1, ed2;
    View view;
    Context mcontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__home, container, false);

        //mcontext = getActivity();


        bt_log = (Button) view.findViewById(R.id.btlogin);
        bt_create = (Button) view.findViewById(R.id.btcreate);

        insta=(ImageButton)view.findViewById(R.id.insta);
        google=(ImageButton)view.findViewById(R.id.google);
        fb=(ImageButton)view.findViewById(R.id.fb);

        ed1 = (EditText) view.findViewById(R.id.ed1);
        ed2 = (EditText) view.findViewById(R.id.ed2);


        bt_log.setOnClickListener(this);
        bt_create.setOnClickListener(this);
        fb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.facebook.com"));
                startActivity(intent);

            }
        });
        google.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/hostelavie.hostels?utm_source=ig_profile_share&igshid=19nmlyzw05x4o"));
                startActivity(intent);


            }
        });



        return view;
    }

    @Override
    public void onClick(View v)
    {

        users = ed1.getText().toString();
        password = ed2.getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(mcontext);
        Cursor c = databaseHelper.chklogin("AccountDetail", new String[]{"user_Id", "password"}, new String[]{users, password});

        if (v == bt_log)
        {
            // if (ed1.getText().toString().equals("admin") && ed2.getText().toString().equals("12345")) {

            if (c != null && c.getCount() > 0)
            {
                Toast.makeText(mcontext, "Redirecting....", Toast.LENGTH_SHORT).show();
                Intent obj = new Intent(mcontext, LogIn.class);
                obj.putExtra("LID",users);
                obj.putExtra("PD", password);
                ed1.setText("");
                ed2.setText("");
                startActivity(obj);

            } else {
                Toast.makeText(mcontext, "wrong credential", Toast.LENGTH_SHORT).show();

            }
        }

        if (v == bt_create)
        {
            Intent obj = new Intent(mcontext, CreateNewAccount.class);
            startActivity(obj);


        }
    }
}