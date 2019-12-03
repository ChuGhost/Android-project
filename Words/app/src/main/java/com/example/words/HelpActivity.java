package com.example.words;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button reback=(Button) findViewById(R.id.returnback);
        reback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help1=new Intent(HelpActivity.this, MainActivity.class);
                startActivity(help1);
            }
        });
    }
}
