package com.example.musicplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyData extends SQLiteOpenHelper {
    public static final String cr = "create table mysongs ("
            +"id integer primary key autoincrement,"
            +"path text default null,"
            +"name text)"
            ;
    private Context mcontext;
    public MyData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
            mcontext=context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cr);
    }
}