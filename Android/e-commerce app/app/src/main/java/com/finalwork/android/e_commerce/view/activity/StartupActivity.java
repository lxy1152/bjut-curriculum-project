package com.finalwork.android.e_commerce.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.finalwork.android.e_commerce.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StartupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread startMain = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        startMain.start();
    }
}
