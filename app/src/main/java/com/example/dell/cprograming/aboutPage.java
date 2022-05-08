package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class aboutPage extends AppCompatActivity implements View.OnClickListener{

    private ImageButton backButton;
    private WebView webView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        backButton = findViewById(R.id.backButtonId);
        webView = findViewById(R.id.webId);
        button = findViewById(R.id.feedbackId);
        backButton.setOnClickListener(this);
        button.setOnClickListener(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/aboutpage.html");
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.feedbackId){
            Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
            intent.putExtra("tag","about");
            startActivity(intent);
            finish();
        }
    }
}