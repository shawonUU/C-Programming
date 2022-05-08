package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainCodePagActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton backButton;
    public Button codeList,compilor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_code_pag);
        backButton = findViewById(R.id.backButtonId);
        codeList = findViewById(R.id.codeListButtonId);
        compilor = findViewById(R.id.compilerButtonId);
        backButton.setOnClickListener(this);
        codeList.setOnClickListener(this);
        compilor.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        fileList();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            fileList();
        }
        if(v.getId()==R.id.codeListButtonId){
            Intent intent = new Intent(getApplicationContext(),CodePage.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.compilerButtonId){
            Intent intent = new Intent(getApplicationContext(),WebView.class);
            intent.putExtra("tag","https://techiedelight.com/compiler/");
            startActivity(intent);
        }
    }
}