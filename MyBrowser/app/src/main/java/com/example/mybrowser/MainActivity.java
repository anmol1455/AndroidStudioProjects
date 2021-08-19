package com.example.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button search;
    EditText ed1;
    WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.se);
        ed1 = (EditText) findViewById(R.id.edit);
        w1 = (WebView) findViewById(R.id.web);

        w1.setWebViewClient(new MyBrowser());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ed1.getText().toString();
                w1.getSettings().setLoadsImagesAutomatically(true);
                w1.getSettings().setJavaScriptEnabled(true);
                w1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                w1.loadUrl(url);
            }
        });

    }

    private class MyBrowser extends WebViewClient {
        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}



