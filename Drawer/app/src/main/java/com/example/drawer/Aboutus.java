package com.example.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Aboutus extends Fragment implements View.OnClickListener {

    Button btnfrag;
    View view;
    Context mcontext;
    //Fragment frag;

    public Aboutus() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        mcontext=getActivity();

        btnfrag=(Button) view.findViewById(R.id.btn);
        btnfrag.setOnClickListener(this);

        // Inflate the layout for this fragment

       return view;
    }

    @Override
    public void onClick(View v) {
        Contactus contactus= new Contactus();

        if(v==btnfrag)
        {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container,contactus).commit();
        }

    }
}
