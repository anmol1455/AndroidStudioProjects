package com.example.akshitavishwakarma.hostel_app;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddPhotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photos);
    }
}
