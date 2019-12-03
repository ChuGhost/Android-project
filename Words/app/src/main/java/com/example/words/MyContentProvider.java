package com.example.words;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Switch;

public class MyContentProvider extends ContentProvider {
    public static final int WORDS_DIR=1;
    private static UriMatcher uriMatcher;
    private MyData dbHelper;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.words.MyContentProvider","mywords",WORDS_DIR);
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("mywords",selection,selectionArgs);
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch(uriMatcher.match(uri)){
            case WORDS_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.words.MyContentProvider.mywords";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.insert("mywords",null,values);
        // TODO: Implement this to handle requests to insert a new row.
        return uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper=new MyData(getContext(),"words.db",null,1);
        System.out.println("创建contentprovider！");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //System.out.println("查询操作！");
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                cursor = db.query("mywords",projection,selection,selectionArgs,null,null,sortOrder);
                break;
                default:
                    break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.update("mywords",values,selection,selectionArgs);
        return 0;
    }
}
