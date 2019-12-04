package com.example.test3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView=(TextView)findViewById(R.id.two);
        Button button=(Button)findViewById(R.id.test2);
        Intent intent1=getIntent();
        String data=intent1.getStringExtra("data1");
        textView.setText(textView.getText()+"\n"+data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String data3=data.getStringExtra("return");
                    Log.d("return",data3);
                    TextView textView=(TextView)findViewById(R.id.two);
                    textView.setText(textView.getText()+"\n"+data3);
                }
               break;
            default:
        }
    }
}
