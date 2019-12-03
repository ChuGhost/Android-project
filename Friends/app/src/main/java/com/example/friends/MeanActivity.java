package com.example.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeanActivity extends AppCompatActivity {
private MyData dbhelper;
String aname=null;
String aphonenumber=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean);
        TextView textView1=(TextView)findViewById(R.id.myname);
        TextView textView2=(TextView)findViewById(R.id.myphonenumber);
        TextView textView3=(TextView)findViewById(R.id.myemail);
        TextView textView4=(TextView)findViewById(R.id.myaddress);
        Button back2=(Button)findViewById(R.id.back2);
        Button checkin=(Button)findViewById(R.id.checkin);
        dbhelper=new MyData(this,"friends.db",null,1);
        Intent intent1=getIntent();
        aname=intent1.getStringExtra("name");
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MeanActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MeanActivity.this,CheckActivity.class);
                intent3.putExtra("aname",aname);
                startActivity(intent3);
            }
        });
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        Cursor cursor=db.query("myfriends",null,"name = ? ",new String[]{aname},null,null,null);
        if(cursor.moveToFirst()){
            do{
                textView1.setText(cursor.getString(cursor.getColumnIndex("name")));
                String number=cursor.getString(cursor.getColumnIndex("phonenumber"));
                textView2.setText(number);
                aphonenumber=number;
                textView3.setText(cursor.getString(cursor.getColumnIndex("email")));
                textView4.setText(cursor.getString(cursor.getColumnIndex("address")));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mean,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.talk:
                Intent intent18=new Intent(Intent.ACTION_DIAL);
                intent18.setData(Uri.parse("tel:"+aphonenumber));
                startActivity(intent18);
                break;
            case R.id.text:
                Intent intent20=new Intent(Intent.ACTION_VIEW,Uri.parse("smsto:"+aphonenumber));
                startActivity(intent20);
                break;
        }
        return true;
    }
}
