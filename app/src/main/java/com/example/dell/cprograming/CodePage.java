package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class CodePage extends AppCompatActivity implements View.OnClickListener {

   private ImageButton imageButton;
    List<String> list;
    ListView listView;
    SearchView searchView;
    String[] headlineArray;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_page);
        imageButton = findViewById(R.id.backButtonId);
        listView = findViewById(R.id.listViewId);
        searchView = findViewById(R.id.searchViewId);
        imageButton.setOnClickListener(this);
         setDataOnListView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                listView.setAdapter(adapter);
                return false;
            }
        });
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String fileName = adapter.getItem(position);
                 fileName = convertString(fileName);
                 Intent intent = new Intent(CodePage.this,DisplayData.class);
                 intent.putExtra("tag",fileName);
                 startActivity(intent);
             }
         });

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

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainCodePagActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
            Intent intent = new Intent(CodePage.this,MainCodePagActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void setDataOnListView(){
        list = new ArrayList<>();
        headlineArray = getResources().getStringArray(R.array.code);
        for(String item: headlineArray){list.add(item);}
        adapter = new ArrayAdapter<>(this,R.layout.child_layout,R.id.childTextViewId,list);
        listView.setAdapter(adapter);
    }
}
