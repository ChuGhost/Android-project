package com.example.words;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeanActivity extends AppCompatActivity {
    private MyData dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean);
        Button back=(Button)findViewById(R.id.back1);
        TextView textView=(TextView)findViewById(R.id.meaning);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeanActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        dbHelper=new MyData(this,"words.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Intent intent=getIntent();
        String eng=intent.getStringExtra("english");
        String ch=intent.getStringExtra("chinese");
        Cursor cursor=db.query("mywords",new String[]{"english","chinese","meaning"},"english=? and chinese=?",new String[]{eng,ch},null,null,null);
        if(cursor.moveToFirst()){
            do{
                String english1=cursor.getString(cursor.getColumnIndex("english"));
                String chinese1=cursor.getString(cursor.getColumnIndex("chinese"));
                String meaning1=cursor.getString(cursor.getColumnIndex("meaning"));
                textView.setText("英文:"+english1+"\n"+"中文"+chinese1+"\n"+"示例："+meaning1);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
