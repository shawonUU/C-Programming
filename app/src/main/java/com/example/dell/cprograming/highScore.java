package com.example.dell.cprograming;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class highScore extends AppCompatActivity implements View.OnClickListener{

    HashMap<Integer,String> mp = new HashMap<Integer, String>();
    HashMap<Integer,Integer> mp1 = new HashMap<Integer, Integer>();
    ArrayList<String> listdata = new ArrayList<>();
    private ListView listView;
    private TextView textView;
    private ImageButton backButton;
    private Button clearButton;
    private LinearLayout linearLayout,linearLayout2;
    SearchView searchView;
    private String playerName;
    private int hiScore;
    database myDatabase;
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        listView = findViewById(R.id.listViewId);
        backButton = findViewById(R.id.backButtonId);
        clearButton = findViewById(R.id.clearButtonID);
        textView = findViewById(R.id.highScoreTaxtViweId);
        searchView = findViewById(R.id.searchViewId);
        linearLayout = findViewById(R.id.buttonTextViweId);
        linearLayout2 = findViewById(R.id.searchLayoutId);
        myDatabase = new database(this);
        backButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        storData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),QuizePage.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(getApplicationContext(),QuizePage.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.clearButtonID){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Do you want clear score history?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    myDatabase.deleteData();
                    Intent intent = new Intent(getApplicationContext(),highScore.class);
                    startActivity(intent);
                    dialog.cancel();
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setCancelable(false);
           // builder.setCanceledOnTouchOutside(false);
            builder.show();
        }
    }
    public void storData(){
        Cursor cursor = myDatabase.getAllValue();
        if(cursor.getCount()==0){
            listdata.add("No Score Saved");
        }
        else{
            int dx=1;
            while (cursor.moveToNext()){
                mp.put(dx,cursor.getString(0));
                mp1.put(dx,cursor.getInt(1));
                dx++;
            }
            Map<Integer, Integer> mp2 = sortByValue(mp1);
            for (Integer key : mp2.keySet()) {
                listdata.add("Name:  "+mp.get(key)+"\n"+"Score:  "+String.valueOf(mp2.get(key)));
                hiScore = mp2.get(key);
                playerName = mp.get(key);
            }
        }
        adapter = new ArrayAdapter<>(this,R.layout.child_layout,R.id.childTextViewId,listdata);
        listView.setAdapter(adapter);
        if(playerName!=null){linearLayout.setVisibility(View.VISIBLE);linearLayout2.setVisibility(View.VISIBLE);textView.setText("Heigh Score: "+hiScore+"\nPlayer Name: "+playerName);}
        else{linearLayout.setVisibility(View.GONE);linearLayout2.setVisibility(View.GONE);}
    }

    public HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
    {
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
