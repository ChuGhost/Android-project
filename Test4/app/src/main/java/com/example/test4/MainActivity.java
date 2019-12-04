package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button in=(Button)findViewById(R.id.in);
        Button out=(Button)findViewById(R.id.out);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText number=(EditText)findViewById(R.id.number);
        final TextView result=(TextView)findViewById(R.id.view);
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name",name.getText().toString());
                editor.putString("number",number.getText().toString());
                editor.apply();
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String name=pref.getString("name","小明");
                String number=pref.getString("number","2017011110");
                result.setText(result.getText()+"姓名："+name+"学号:"+number);
            }
        });
    }
}
