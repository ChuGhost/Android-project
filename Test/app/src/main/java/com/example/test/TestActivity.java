package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.LinearLayout;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Button b1=(Button)findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent =new Intent(TestActivity.this,Test2Activity.class);
               // startActivity(intent);
                final View login=getLayoutInflater().inflate(R.layout.activity_test2,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(TestActivity.this);
                builder.setTitle("用户登录");
                builder.setMessage("请输入用户名和密码");
                builder.setView(login);
                builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText username=(EditText) login.findViewById(R.id.username);
                        EditText password=(EditText)login.findViewById(R.id.password);
                        //String test=username.getText().toString()+password.getText().toString();
                       // Toast.makeText(TestActivity.this,test,Toast.LENGTH_SHORT).show();
                        if(username.getText().toString().equals("123")){
                            if(password.getText().toString().equals("456")){
                                Toast.makeText(TestActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(TestActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(TestActivity.this,"该用户不存在!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                   }
                        });
                builder.create().show();
            }
        });
    }
}
