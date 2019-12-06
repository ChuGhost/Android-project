package com.example.content;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Button button=(Button)findViewById(R.id.addin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editeng=(EditText)findViewById(R.id.addeng);
                EditText editch=(EditText)findViewById(R.id.addch);
                ContentValues contentValues=new ContentValues();
                contentValues.put("english",editeng.getText().toString());
                contentValues.put("chinese",editch.getText().toString());
                Uri uri = Uri.parse("content://com.example.words.MyContentProvider/mywords");
                getContentResolver().insert(uri,contentValues);
                Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
