package com.bibek.audiophile.singletonclass;

import android.media.MediaPlayer;
// this is a singleton class


public class SongMediaPlayer {

    private static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if(instance == null) {
              instance = new MediaPlayer();
        }
        return instance;
    }

    public static int currentIndex = -1;

}
