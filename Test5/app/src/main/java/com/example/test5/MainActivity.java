 package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.security.PrivateKey;

 public class MainActivity extends AppCompatActivity {
    ProgressBar bar=null;
    Button handle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=(ProgressBar)findViewById(R.id.bar);
        handle=(Button)findViewById(R.id.change);
        handle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                myhandler.post(update_thread);
            }
        });
    }
     Handler myhandler=new Handler(){
        public void handleMessage(Message msg){
            bar.setProgress(msg.arg1);
            myhandler.post(update_thread);
        }
     };
    Runnable update_thread =new Runnable() {
        int i=0;
        @Override
        public void run() {
            i += 10;
            Message msg = myhandler.obtainMessage();
            msg.arg1 = i;
            try{
               Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            e.printStackTrace();
            }
            myhandler.sendMessage(msg);
            if(i==100){
                myhandler.removeCallbacks(update_thread);
            }
        }
    };
}
