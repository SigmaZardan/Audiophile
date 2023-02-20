package com.bibek.audiophile.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService  extends Service {

    private IBinder mBinder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Bind", "method");
        return mBinder;
    }

    public class MyBinder extends Binder {

       public MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ONSTART", "method");
        return super.onStartCommand(intent, flags, startId);
    }
}
