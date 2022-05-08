package com.example.dell.cprograming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "my_data.db";
    private static final String TABLE_NAME = "score_update";
    private static final int VERSION_NUMBER = 1;

    private static final String TABLE_CALL1_NAME = "Name";
    private static final String TABLE_CALL2_NAME = "Score";
    private static final String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;
    private static final String DELET_TABLE_DATA = "DELETE FROM "+TABLE_NAME;

    private static final String CREAT_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+TABLE_CALL1_NAME+" VARCHAR(250), "+TABLE_CALL2_NAME+" INTEGER );";
    private Context context;
    public database(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREAT_TABLE);
            Toast.makeText(context,"Table has Created",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context,"Excption: "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  long insertDataToTable(String name,int value){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CALL1_NAME,name);
        contentValues.put(TABLE_CALL2_NAME,value);
        long rowid = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowid;
    }

    public Cursor getAllValue(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELET_TABLE_DATA);
        db.close();
    }
}
