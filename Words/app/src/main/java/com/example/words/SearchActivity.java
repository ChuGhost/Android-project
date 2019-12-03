package com.example.words;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private MyData dbHelper;
    private List<Words> wordsList=new ArrayList<>();
    private List<Words> wordsList1=new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        dbHelper=new MyData(this,"words.db",null,1);
        final EditText eng=(EditText)findViewById(R.id.checkeng);
        final EditText ch=(EditText)findViewById(R.id.checkch);
        Button search=(Button)findViewById(R.id.checkin);
        final Button delete=(Button)findViewById(R.id.delete);
        Button edit=(Button)findViewById(R.id.edit);
        Button ssearch=(Button)findViewById(R.id.ssearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wordsList.isEmpty()){
                    wordsList.clear();
                }
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                String english=eng.getText().toString();
                String chinese=ch.getText().toString();
                Cursor cursor1=db.query("mywords",new String[]{"english","chinese"},"english=?",new String[]{english},null,null,null);
                Cursor cursor2=db.query("mywords",new String[]{"english","chinese"},"chinese=?",new String[]{chinese},null,null,null);
                if(cursor1.moveToFirst()){
                    do{
                        String english1=cursor1.getString(cursor1.getColumnIndex("english"));
                        String chinese1=cursor1.getString(cursor1.getColumnIndex("chinese"));
                        Words words=new Words(english1,chinese1);
                        wordsList.add(words);
                    }while (cursor1.moveToNext());
                }
                cursor1.close();
                if(cursor2.moveToFirst()){
                    do{
                        String english2=cursor2.getString(cursor2.getColumnIndex("english"));
                        String chinese2=cursor2.getString(cursor2.getColumnIndex("chinese"));
                        Words words=new Words(english2,chinese2);
                        if(wordsList.isEmpty()) {
                            wordsList.add(words);
                            }
                        else {
                            if (!words.getEnglish().equals(wordsList.get(0).getEnglish())) {
                                wordsList.add(words);
                            }
                        }
                    }while (cursor2.moveToNext());
                }
                cursor2.close();
                final WordsAdapter wordsAdapter=new WordsAdapter(SearchActivity.this,R.layout.words,wordsList);
                listView=(ListView)findViewById(R.id.search_view);
                listView.setAdapter(wordsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Words words=wordsList.get(i);
                    Intent intent1=new Intent(SearchActivity.this,MeanActivity.class);
                    intent1.putExtra("english",words.getEnglish());
                    intent1.putExtra("chinese",words.getChinese());
                    startActivity(intent1);
                    }
                });
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Words word=wordsList.get(i);
                        final String english=word.getEnglish();
                        final String chinese=word.getChinese();
                        AlertDialog.Builder builder=new AlertDialog.Builder(SearchActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("请确认您的操作");
                        builder.setCancelable(false);
                        builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SQLiteDatabase db=dbHelper.getWritableDatabase();
                                db.execSQL("delete from mywords where english = ? and chinese = ? ",new String[]{english,chinese});
                                Toast.makeText(SearchActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                        return true;
                    }
                });
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchActivity.this,CheckActivity.class);
                Words word1=wordsList.get(0);
                intent.putExtra("english",word1.getEnglish());
                intent.putExtra("chinese",word1.getChinese());
                startActivity(intent);
            }
        });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(SearchActivity.this,MainActivity.class);
               startActivity(intent);
           }
       });
       ssearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(!wordsList.isEmpty()){
                   wordsList.clear();
               }
               SQLiteDatabase db=dbHelper.getWritableDatabase();
               EditText eng1=(EditText)findViewById(R.id.checkeng);
               EditText ch1=(EditText)findViewById(R.id.checkch);
               String english=eng1.getText().toString();
               String chinese=ch1.getText().toString();
               //System.out.println(english+chinese);
               if(!(english==null||english.equals(""))){
                   Cursor cursor1 = db.query("mywords", new String[]{"english", "chinese"}, "english like ?", new String[]{"%" + english + "%"}, null, null, null);
                   if (cursor1.moveToFirst()) {
                       do {
                           String english1 = cursor1.getString(cursor1.getColumnIndex("english"));
                           String chinese1 = cursor1.getString(cursor1.getColumnIndex("chinese"));
                           Words word1 = new Words(english1, chinese1);
                           wordsList.add(word1);
                       } while (cursor1.moveToNext());
                   }
                   cursor1.close();
               }
               if(!(chinese==null||chinese.equals(""))){
                   Cursor cursor2 = db.query("mywords", new String[]{"english", "chinese"}, "chinese like ?", new String[]{"%" + chinese + "%"}, null, null, null);
                   if (cursor2.moveToFirst()) {
                       do {
                           String english2 = cursor2.getString(cursor2.getColumnIndex("english"));
                           String chinese2 = cursor2.getString(cursor2.getColumnIndex("chinese"));
                           Words word2 = new Words(english2, chinese2);
                           if(wordsList.isEmpty()){
                               wordsList1.add(word2);
                           }
                           else {
                               wordsList1.add(word2);
                               for (Words w : wordsList) {
                                   if (w.getEnglish()==word2.getEnglish()) {
                                       wordsList1.remove(word2);
                                   }
                               }
                           }
                       } while (cursor2.moveToNext());
                   }
                   cursor2.close();
               }
               for(Words w1:wordsList1) {
                   System.out.println(w1.getEnglish()+w1.getChinese());
               }
              wordsList.addAll(wordsList1);
               //for(Words w:wordsList) {
              //     System.out.println(w.getEnglish()+w.getChinese());
              // }
               WordsAdapter wordsAdapter=new WordsAdapter(SearchActivity.this,R.layout.words,wordsList);
               listView=(ListView)findViewById(R.id.search_view);
               listView.setAdapter(wordsAdapter);
           }
       });
    }
}
