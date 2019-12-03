package com.example.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private MyData dbHelper;
    private List<String> allsongs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //final EditText songname=(EditText)findViewById(R.id.name);
       // Button input=(Button)findViewById(R.id.input);
        Button back=(Button)findViewById(R.id.back);
        ListView alllist=(ListView)findViewById(R.id.alllist);
        inmit();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AddActivity.this,android.R.layout.simple_list_item_1,allsongs);
        alllist.setAdapter(adapter);
        dbHelper=new MyData(this,"songs.db",null,1);
       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        alllist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                builder.setTitle("提示");
                builder.setMessage("请确认您的操作");
                builder.setCancelable(false);
                final int position=i;
                builder.setNegativeButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=allsongs.get(position);
                        SQLiteDatabase db=dbHelper.getWritableDatabase();
                        Cursor cursor=db.query("mysongs",null,"name = ?",new String[]{name},null,null,null);
                        if(cursor.getCount()==0) {
                            db.execSQL("insert into mysongs (name) values (?)", new String[]{name});
                            Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AddActivity.this,"该歌曲已存在！",Toast.LENGTH_SHORT).show();
                        }
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
    public void inmit(){
        if(allsongs.size()>0){
            allsongs.clear();
        }
        Cursor cursor= getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null, null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()){
            do{
            String name=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            if(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)).contains("/storage/emulated/0/")) {
                allsongs.add(name);
            }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
