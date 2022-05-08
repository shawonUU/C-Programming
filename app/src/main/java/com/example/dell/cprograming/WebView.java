package com.example.dell.cprograming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebView extends AppCompatActivity {
    private android.webkit.WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (android.webkit.WebView) findViewById(R.id.webViewId);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String value = bundle.getString("tag");

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(value);
        }
    }
}