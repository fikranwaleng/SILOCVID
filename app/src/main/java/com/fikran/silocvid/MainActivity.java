package com.fikran.silocvid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Delayed;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Thread thread = new Thread(){
           public void run(){
               try {
                   sleep(3000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }finally {
                   startActivity(new Intent(MainActivity.this,DetailInformasi.class));
                   finish();
               }
           }
       };
       thread.start();
    }
}
