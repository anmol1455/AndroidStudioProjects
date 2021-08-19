package com.example.myvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button pvdo;
    VideoView vdov;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pvdo=(Button)findViewById(R.id.btnPlay);
        vdov=(VideoView)findViewById(R.id.vdo);
    }

    public void playvideo(View view) {
        MediaController m=new MediaController(this);
        vdov.setMediaController(m);
        String Path="android.resource://com.example.myvideo/"+R.raw.trial;

        Uri u= Uri.parse(Path);
        vdov.setVideoURI(u);
        vdov.start();

    }
}
