package com.example.content;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    List<String> list1=new ArrayList<>();
    List<String> list2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView1 = (ListView) findViewById(R.id.mylist1);
        ListView listView2 = (ListView) findViewById(R.id.mylist2);
        Button add=(Button)findViewById(R.id.add1);
        Uri uri = Uri.parse("content://com.example.words.MyContentProvider/mywords");
        Cursor cursor = getContentResolver().query(uri,null, null, null, null);
        System.out.println(getContentResolver().getType(uri));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    String english = cursor.getString(cursor.getColumnIndex("english"));
                    String chinese = cursor.getString(cursor.getColumnIndex("chinese"));
                    list1.add(english);
                    list2.add(chinese);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        else {
            System.out.println("未找到");
        }
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
    }
}
