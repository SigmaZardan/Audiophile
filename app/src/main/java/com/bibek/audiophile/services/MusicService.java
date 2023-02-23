package com.bibek.audiophile.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bibek.audiophile.ui.musicPlayerActivity.MusicPlayerActivity;

public class MusicService  extends Service {

    private IBinder mBinder = new MyBinder();
    public static final String ACTION_NEXT = "NEXT";
    public static final String ACTION_PREV= "PREV";
    public static final String ACTION_PLAY = "PLAY";
    MusicPlayerActivity musicPlayerActivity;


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
        String actionName = intent.getStringExtra("myActionName");
        if(actionName != null) {
            if(musicPlayerActivity!= null) {
                switch (actionName){
                    case ACTION_PLAY:
                        musicPlayerActivity.pausePlaySong();

                        break;
                    case ACTION_NEXT:
                        musicPlayerActivity.playNextSong();
                        break;

                    case ACTION_PREV:
                        musicPlayerActivity.playPreviousSong();
                        break;
                }

            }


        }

        return START_STICKY;
    }
    public void setCallBack(MusicPlayerActivity musicPlayerActivity) {
        this.musicPlayerActivity = musicPlayerActivity;
    }
}
