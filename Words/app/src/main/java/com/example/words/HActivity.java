package com.example.words;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HActivity extends AppCompatActivity {
    private MyData dbHelper;
    private List<Words> wordsList=new ArrayList<>();
    private List<String> che=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heng);
        final TextView textView=(TextView)findViewById(R.id.hchinese);
        Button button=(Button)findViewById(R.id.s);
        init();
        WordsAdapter wordsAdapter=new WordsAdapter(HActivity.this,R.layout.words,wordsList);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(HActivity.this,android.R.layout.simple_list_item_1,che);
        ListView listView=(ListView)findViewById(R.id.mylist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Words word=wordsList.get(i);
                String chinese=word.getChinese();
                textView.setText("翻译："+"\n"+chinese);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void init(){
        dbHelper=new MyData(this,"words.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("mywords",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String english=cursor.getString(cursor.getColumnIndex("english"));
                String chinese=cursor.getString(cursor.getColumnIndex("chinese"));
                Words words=new Words(english,chinese);
                wordsList.add(words);
                che.add(words.getEnglish());
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
