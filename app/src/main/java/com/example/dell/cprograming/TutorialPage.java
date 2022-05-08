package com.example.dell.cprograming;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialPage extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButton;
    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;
    List<String>headerDatalist;
    HashMap<String,List<String>>dataHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_page);
        imageButton = findViewById(R.id.backButtonId);
        headerDatalist = new ArrayList<>();
        dataHeader = new HashMap<>();
        expandableListView = (ExpandableListView) findViewById(R.id.expantableListViewId);
        customAdapter = new CustomAdapter(this,headerDatalist,dataHeader);
        expandableListView.setAdapter(customAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String childString = dataHeader.get(headerDatalist.get(groupPosition)).get(childPosition);
                childString = convertString(childString);
                Intent intent = new Intent(TutorialPage.this,DisplayData.class);
                intent.putExtra("tag",childString);
                startActivity(intent);
                return false;
            }
        });
        prepareData();
        imageButton.setOnClickListener(this);
    }
    String convertString(String s){
        int ln = s.length();
        String ss="";
        char c;
        for(int i=0; i<ln; i++){
            c = s.charAt(i);
            if(c>=65&&90>=c){c += 32;}
            if(c>=97&&122>=c) ss += c;
            else{continue;}
        }
        ss += ".html";
        return ss;
    }

    public void prepareData(){
        headerDatalist.add(getString(R.string.group0));
        headerDatalist.add(getString(R.string.group1));
        headerDatalist.add(getString(R.string.group2));
        headerDatalist.add(getString(R.string.group3));
        headerDatalist.add(getString(R.string.group4));
        headerDatalist.add(getString(R.string.group5));
        headerDatalist.add(getString(R.string.group6));

        String[] headerArray;
        List<String> list0 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group0);
        for(String item: headerArray){
           list0.add(item);
        }
        List<String> list1 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group1);
        for(String item: headerArray){
           list1.add(item);
        }
        List<String> list2 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group2);
        for(String item: headerArray){
            list2.add(item);
        }
        List<String> list3 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group3);
        for(String item: headerArray){
            list3.add(item);
        }
        List<String> list4 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group4);
        for(String item: headerArray){
            list4.add(item);
        }
        List<String> list5 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group5);
        for(String item: headerArray){
            list5.add(item);
        }
        List<String> list6 = new ArrayList<>();
        headerArray = getResources().getStringArray(R.array.group6);
        for(String item: headerArray){
            list6.add(item);
        }
        dataHeader.put(headerDatalist.get(0),list0);
        dataHeader.put(headerDatalist.get(1),list1);
        dataHeader.put(headerDatalist.get(2),list2);
        dataHeader.put(headerDatalist.get(3),list3);
        dataHeader.put(headerDatalist.get(4),list4);
        dataHeader.put(headerDatalist.get(5),list5);
        dataHeader.put(headerDatalist.get(6),list6);
        customAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(TutorialPage.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
