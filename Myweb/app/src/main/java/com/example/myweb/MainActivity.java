package com.example.myweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
WebView webv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webv=(WebView)findViewById(R.id.web);
       // webv.loadUrl("https://www.facebook.com/");
        webv.loadUrl("file:///android_asset/Form1.html");
        //String Data="<html><body><h1>Anmol</h1></body></html>";
       // webv.loadData(Data,text/html,"UTF-8");
    }
}
