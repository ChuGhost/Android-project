package com.example.friends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyData extends SQLiteOpenHelper {
    private Context mcontext;
    public static final String cr = "create table myfriends ("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"phonenumber text default null,"
            +"email text default null,"
            +"address text default null)"
            ;
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