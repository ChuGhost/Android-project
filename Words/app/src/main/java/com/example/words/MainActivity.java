package com.example.words;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyData dbHelper;
    private List<Words> wordsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        WordsAdapter wordsAdapter=new WordsAdapter(MainActivity.this,R.layout.words,wordsList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(wordsAdapter);
       /* dbHelper=new MyData(this,"words.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("mywords",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String english=cursor.getString(cursor.getColumnIndex("english"));
                String chinese=cursor.getString(cursor.getColumnIndex("chinese"));
                Words words=new Words(english,chinese);
                wordsList.add(words);
            }while (cursor.moveToNext());
        }
        cursor.close();
        WordsAdapter wordsAdapter=new WordsAdapter(MainActivity.this,R.layout.words,wordsList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(wordsAdapter);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.remove:
                Intent hintent=new Intent(MainActivity.this,HActivity.class);
                startActivity(hintent);
                break;
            case R.id.search:
                Intent sintent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(sintent);
                break;
            case R.id.check:
                break;
            case R.id.help:
                Intent help=new Intent(MainActivity.this, HelpActivity.class);
                startActivity(help);
                break;
                default:
        }
        return true;
    }
    public void init(){
        dbHelper=new MyData(this,"words.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("mywords",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String english=cursor.getString(cursor.getColumnIndex("english"));
                String chinese=cursor.getString(cursor.getColumnIndex("chinese"));
                Words words=new Words(english,chinese);
                wordsList.add(words);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //WordsAdapter wordsAdapter=new WordsAdapter(MainActivity.this,R.layout.words,wordsList);
       // ListView listView=(ListView)findViewById(R.id.list_view);
        //listView.setAdapter(wordsAdapter);
    }
}
