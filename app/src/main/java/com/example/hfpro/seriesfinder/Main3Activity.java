package com.example.hfpro.seriesfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Main3Activity extends AppCompatActivity {

    WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView = (WebView) findViewById(R.id.webView) ;

        String url = getIntent().getStringExtra("URL");

        webView.loadUrl(url);
    }
}
