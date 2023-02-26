package com.bibek.audiophile.ui.splashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bibek.audiophile.R;
import com.bibek.audiophile.ui.firstScreenActivity.FirstScreenActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // handles the parallel processing threads (asynchronous threading)
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, FirstScreenActivity.class);
                        startActivity(intent);
                        // finish method is used to delete this activity from the stack
                        // which means it will run only once
                        finish();
                    }
                },
                1500
        );
    }
}