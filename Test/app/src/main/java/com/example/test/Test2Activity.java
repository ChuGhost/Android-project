package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Button b=(Button)findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username=(EditText)findViewById(R.id.username);
                EditText password=(EditText)findViewById(R.id.password);
                if(username.getText().toString().equals("123")){
                    if(password.getText().toString().equals("456")){
                        Toast.makeText(Test2Activity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Test2Activity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Test2Activity.this,"该用户不存在!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
