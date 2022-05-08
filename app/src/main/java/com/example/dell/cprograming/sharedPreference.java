package com.example.dell.cprograming;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class sharedPreference extends AppCompatActivity {
    private Context context;
    public sharedPreference(Context context) {
        this.context = context;
    }
    public void putContinue(int v){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("continue",v);
        editor.commit();
    }
    public void putScore(int v){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score",v);
        editor.commit();
    }
    public void putIndex(int v){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("index",v);
        editor.commit();
    }
    public void putTime(int v){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("time",v);
        editor.commit();
    }

    public int getContinue(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        return  sharedPreferences.getInt("continue",0);
        //return 0;
    }
    public int getScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        return  sharedPreferences.getInt("score",0);
    }
    public int getIndex(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
       return  sharedPreferences.getInt("index",-1);
    }
    public int getTime(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",context.MODE_PRIVATE);
        return  sharedPreferences.getInt("time",0);
    }
}
