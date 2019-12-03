package com.example.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {
    private MyData dbhelper;
    private String aname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        final EditText editText1=(EditText) findViewById(R.id.cname);
        final EditText editText2=(EditText)findViewById(R.id.cphonenumber);
        final EditText editText3=(EditText)findViewById(R.id.cemail);
        final EditText editText4=(EditText)findViewById(R.id.caddress);
        Intent intent2=getIntent();
        aname=intent2.getStringExtra("aname");
        editText1.setText(aname);
        dbhelper=new MyData(this,"friends.db",null,1);
        SQLiteDatabase db1=dbhelper.getWritableDatabase();
        Cursor cursor1=db1.query("myfriends",null,"name=?",new String[]{aname},null,null,null);
        if(cursor1.moveToFirst()){
            do{
                editText2.setText(cursor1.getString(cursor1.getColumnIndex("phonenumber")));
                editText3.setText(cursor1.getString(cursor1.getColumnIndex("email")));
                editText4.setText(cursor1.getString(cursor1.getColumnIndex("address")));
            }while (cursor1.moveToNext());
        }
        cursor1.close();
        Button back1=(Button)findViewById(R.id.back1);
        Button check=(Button)findViewById(R.id.check);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CheckActivity.this,MeanActivity.class);
                intent1.putExtra("name",aname);
                startActivity(intent1);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SQLiteDatabase db=dbhelper.getWritableDatabase();
            String n=editText1.getText().toString();
            String p=editText2.getText().toString();
            String e=editText3.getText().toString();
            String a=editText4.getText().toString();
            db.execSQL("update myfriends set name=? where name=?",new String[]{n,aname});
            db.execSQL("update myfriends set phonenumber=? where name=?",new String[]{p,aname});
            db.execSQL("update myfriends set email=? where name=?",new String[]{e,aname});
            db.execSQL("update myfriends set address=? where name=?",new String[]{a,aname});
            Toast.makeText(CheckActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            Intent intent3=new Intent(CheckActivity.this,MeanActivity.class);
            intent3.putExtra("name",aname);
            startActivity(intent3);
            }
        });
    }
}
