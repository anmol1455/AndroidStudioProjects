package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LikeFbPage extends Fragment {



    public LikeFbPage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri =Uri.parse("https://www.facebook.com/855353404796898");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like_fb_page, container, false);

    }
}
