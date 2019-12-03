package com.example.words;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {
    private MyData dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Button button=(Button)findViewById(R.id.checkin);
        Button back=(Button)findViewById(R.id.back);
        dbHelper=new MyData(this,"words.db",null,1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1=(EditText)findViewById(R.id.checkeng);
                EditText editText2=(EditText)findViewById(R.id.checkch);
                String english=editText1.getText().toString();
                String chinese=editText2.getText().toString();
                Intent intent=getIntent();
                String olde=intent.getStringExtra("english");
                String oldc=intent.getStringExtra("chinese");
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("update mywords set english=? where english=?",new String[]{english,olde});
                db.execSQL("update mywords set chinese=? where chinese=?",new String[]{chinese,oldc});
                Toast.makeText(CheckActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CheckActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
