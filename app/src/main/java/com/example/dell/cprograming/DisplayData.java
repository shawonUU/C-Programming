package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        webView = (WebView) findViewById(R.id.webViewId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String value = bundle.getString("tag");

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/" + value);
        }
    }
}
