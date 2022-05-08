package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tutorialbutton,codebutton,quizebutton,sharebutton,aboutbutton,feedbackb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tutorialbutton = (Button) findViewById(R.id.tutorialButton);
        codebutton =(Button) findViewById(R.id.codeButton);
        quizebutton = (Button) findViewById(R.id.quizeButton);
        sharebutton = findViewById(R.id.shareButton);
        aboutbutton = findViewById(R.id.aboutButton);
        feedbackb = findViewById(R.id.feedbackButton);

        tutorialbutton.setOnClickListener(this);
        codebutton.setOnClickListener(this);
        quizebutton.setOnClickListener(this);
        sharebutton.setOnClickListener(this);
        aboutbutton.setOnClickListener(this);
        feedbackb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tutorialButton){
            Intent intent = new Intent(MainActivity.this,TutorialPage.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.codeButton){
            Intent intent = new Intent(MainActivity.this,MainCodePagActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.quizeButton){
            Intent intent = new Intent(MainActivity.this,QuizePage.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.shareButton){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This app cane be helpfull for you to learn C programing.\n com.example.dell.cprograming");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share With"));
        }
        if(v.getId()==R.id.feedbackButton){
            Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
            intent.putExtra("tag","main");
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.aboutButton){
            Intent intent = new Intent(getApplicationContext(),aboutPage.class);
            startActivity(intent);
            finish();
        }
    }
}
