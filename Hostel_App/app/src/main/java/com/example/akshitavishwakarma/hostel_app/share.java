package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class share extends Fragment {


    public share() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "hostelDMPG, https://g.co/kgs/gHLtmz";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"whatsapp");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(sharingIntent,"Tell a friend !"));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

}