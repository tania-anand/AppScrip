package com.triviaapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.triviaapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*showing the splash screen for 3 secs*/
        handler.sendEmptyMessageDelayed(101,3000);
    }

    @SuppressLint("HandlerLeak")
    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 101) {
                Intent i = new Intent(SplashActivity.this,GameActivity.class);
                startActivity(i);
                finish();
            }
            }
    };
}
