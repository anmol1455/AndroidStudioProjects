package com.example.akshitavishwakarma.hostel_app;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class gallery extends Fragment implements View.OnClickListener {

    Button b1_addphoto;
    View view;
    Context mcontext;

//    public gallery() {
//
//
//
//        // Required empty public constructor
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mcontext = getActivity();

        b1_addphoto = (Button) view.findViewById(R.id.addphotos);

        b1_addphoto.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(View v) {

        if (v == b1_addphoto) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);


        }
    }
}