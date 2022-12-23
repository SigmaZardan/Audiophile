package com.bibek.audiophile.app;

import android.app.Application;

import com.bibek.audiophile.database.AppDatabase;
import com.bibek.audiophile.database.PlaylistDao;
import com.bibek.audiophile.database.SongDao;

public class App extends Application {


    public static AppDatabase db;
    public static SongDao songDao;
    public static PlaylistDao playlistDao;
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void initDB() {
        db = AppDatabase.getInstance(getApplicationContext());
        songDao = db.songDao();
        playlistDao = db.playlistDao();
    }
}



