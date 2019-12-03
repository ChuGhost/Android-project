package com.example.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private MyData dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button back=(Button)findViewById(R.id.back);
        Button add=(Button)findViewById(R.id.add);
        final EditText name=(EditText) findViewById(R.id.name);
        final EditText phonenumber=(EditText) findViewById(R.id.phonenumber);
        final EditText email=(EditText) findViewById(R.id.email);
        final EditText address=(EditText) findViewById(R.id.address);
        dbhelper=new MyData(this,"friends.db",null,1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friend friend=new Friend();
                SQLiteDatabase db=dbhelper.getWritableDatabase();
                friend.setName(name.getText().toString());
                friend.setPhonenumber(phonenumber.getText().toString());
                friend.setEmail(email.getText().toString());
                friend.setAddress(address.getText().toString());
                ContentValues values=new ContentValues();
                values.put("name",friend.getName());
                values.put("phonenumber",friend.getPhonenumber());
                values.put("email",friend.getEmail());
                values.put("address",friend.getAddress());
                db.insert("myfriends",null,values);
                Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
