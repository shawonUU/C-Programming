package com.example.dell.cprograming;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuizePage extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageButton;
    private Button continueButton,newbutton,highScoreButton;
    database myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize_page);

        myDatabase = new database(this);
        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();

        imageButton = findViewById(R.id.backButtonId);
        continueButton = findViewById(R.id.continueButtonId);
        newbutton = findViewById(R.id.newButtonId);

        highScoreButton = findViewById(R.id.heighScoreButtonId);

        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        if(sharedPreferences.getInt("continue",0)==1){continueButton.setVisibility(View.VISIBLE);}
        else {continueButton.setVisibility(View.GONE);}

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("continue",0);

        imageButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);
        newbutton.setOnClickListener(this);
        highScoreButton.setOnClickListener(this);
    }
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(QuizePage.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.continueButtonId){
            Intent intent = new Intent(QuizePage.this,questionShow.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.newButtonId){
            SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("continue",1);
            editor.putInt("index",-1);
            editor.putInt("score",0);
            editor.putInt("time",0);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),questionShow.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.heighScoreButtonId){
            Intent intent = new Intent(getApplicationContext(),highScore.class);
            startActivity(intent);
            finish();
        }
    }
    public void showData(String titel,String scoreData){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titel);
        builder.setMessage(scoreData);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
