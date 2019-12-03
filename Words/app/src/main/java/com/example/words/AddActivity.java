package com.example.words;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private MyData dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        dbHelper=new MyData(this,"words.db",null,1);
        Button button=(Button)findViewById(R.id.addin);
        final EditText editeng=(EditText)findViewById(R.id.addeng);
        final EditText editch=(EditText)findViewById(R.id.addch);
        final EditText meaning=(EditText)findViewById(R.id.meaning);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                String english=editeng.getText().toString();
                String chinese=editch.getText().toString();
                String me=meaning.getText().toString();
                values.put("english",english);
                values.put("chinese",chinese);
                values.put("meaning",me);
                db.insert("mywords",null,values);
                Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
