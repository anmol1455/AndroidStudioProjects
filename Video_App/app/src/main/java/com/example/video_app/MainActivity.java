package com.example.video_app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video=(VideoView) findViewById(R.id.vdo);

        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(video);

        Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Media/Ab.mp4");
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();
    }
}
