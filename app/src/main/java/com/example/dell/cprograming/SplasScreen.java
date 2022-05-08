package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplasScreen extends AppCompatActivity {

    private ProgressBar progressbar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splas_screen);
        progressbar = (ProgressBar) findViewById(R.id.progressBarID);

        //progressbar.setScaleY(5f);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
                startApp();
            }
        });
        thread.start();
    }
    public void work(){
        for (progress=2; progress<=100; progress+=2){
            try {
                Thread.sleep(100);
                progressbar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public  void startApp(){
        Intent intent = new Intent(SplasScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
