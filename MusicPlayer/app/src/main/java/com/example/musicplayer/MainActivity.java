package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Barrier;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private SeekBar bar;
    private TextView songname;
    private TextView songtime;
    private Timer timer;
    private boolean isChanged=false;
    private SimpleDateFormat format;
    private ListView listView;
    private MyData dbHelper;
    private List<String> songs=new ArrayList<>();
    private String playsong="许嵩 - 雨幕.mp3";
    private int number;
    private boolean sui=false;
    //="许嵩 - 雨幕.mp3"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play=(Button)findViewById(R.id.play);
        Button stop=(Button)findViewById(R.id.stop);
        Button next=(Button)findViewById(R.id.next);
        Button pre=(Button)findViewById(R.id.pre);
        Button pause=(Button)findViewById(R.id.pause);
        Button add=(Button)findViewById(R.id.add);
        Button suiji=(Button)findViewById(R.id.suiji);
        bar=(SeekBar)findViewById(R.id.bar);
        songname=(TextView) findViewById(R.id.name);
        songtime=(TextView) findViewById(R.id.time);
        listView=(ListView)findViewById(R.id.mylist);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
        pause.setOnClickListener(this);
        add.setOnClickListener(this);
        suiji.setOnClickListener(this);
        bar.setOnSeekBarChangeListener(new MyBar());
        format=new SimpleDateFormat("mm:ss");
        init();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,songs);
        listView.setAdapter(adapter);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            initMusicPlayer();
        }
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("请确认您的操作");
                builder.setCancelable(false);
                final int position=i;
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db=dbHelper.getWritableDatabase();
                        db.execSQL("delete from mysongs where name = ? ",new String[]{songs.get(position)});
                        Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        songs.clear();
                        init();
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,songs);
                        listView.setAdapter(adapter);
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
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(sui){
                    int res=(int)(0+Math.random()*(songs.size()-1-0+1));
                    number=res;
                    System.out.println(number);
                    mediaPlayer.reset();
                    playsong=songs.get(number);
                    songname.setText(playsong);
                    initMusicPlayer();
                    play();
                }
                else{
                    next();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                number=i;
                playsong=songs.get(i);
                songname.setText(playsong);
                bar.setProgress(0);
                initMusicPlayer();
                songtime.setText("00:00" + "/" + format.format(mediaPlayer.getDuration()));
            }
        });
    }
    private void initMusicPlayer() {

        File file = new File(Environment.getExternalStorageDirectory(),playsong);
        System.out.println(file.getPath());
        try {
                    mediaPlayer.reset();
                    bar.setProgress(0);
                    mediaPlayer.setDataSource(file.getPath());
                    mediaPlayer.prepare();
                    bar.setMax(mediaPlayer.getDuration());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    //time.setText("00:00"+"/"+format.format(musicplayer.getDuration()));
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   initMusicPlayer();
                }
                else{
                    Toast.makeText(this,"拒绝权限将无法使用程序！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                play();
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMusicPlayer();

                }
                break;
            case R.id.next:
                mediaPlayer.reset();
                if(number==(songs.size()-1)){
                   playsong=songs.get(0);
                   number=0;
                    songname.setText(playsong);
                   initMusicPlayer();
                   play();
                }
                else{
                    playsong=songs.get(number+1);
                    number++;
                    songname.setText(playsong);
                    initMusicPlayer();
                    play();
                }
                break;
            case R.id.pre:
                mediaPlayer.reset();
                if(number==0){
                    playsong=songs.get(songs.size()-1);
                    number=songs.size()-1;
                    songname.setText(playsong);
                    initMusicPlayer();
                    play();
                }
                else{
                    playsong=songs.get(number-1);
                    songname.setText(playsong);
                    number--;
                    initMusicPlayer();
                    play();
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.suiji:
                sui=!sui;
                default:
                    break;
        }
    }
    //(int)(1+Math.random()*(10-1+1))
    public void init(){
        dbHelper=new MyData(this,"songs.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("mysongs",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
               songs.add(cursor.getString(cursor.getColumnIndex("name")));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
            if(timer!=null){
                timer.cancel();
                timer=null;
            }
        }
    }
    public void next(){
        mediaPlayer.reset();
        if(number==(songs.size()-1)){
            playsong=songs.get(0);
            number=0;
            songname.setText(playsong);
            initMusicPlayer();
            play();
        }
        else{
            playsong=songs.get(number+1);
            number++;
            songname.setText(playsong);
            initMusicPlayer();
            play();
        }
    }
    public void play(){
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                Runnable mythread = new Runnable() {
                    @Override
                    public void run() {
                        songtime.setText(format.format(mediaPlayer.getCurrentPosition()) + "/" + format.format(mediaPlayer.getDuration()));
                    }
                };

                @Override
                public void run() {
                    if (!isChanged) {
                        if(mediaPlayer.isPlaying()) {
                            bar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                        runOnUiThread(mythread);
                    }
                }
            }, 0, 1000);
        }
    }
    public class MyBar implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanged=true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            isChanged=false;
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }
}