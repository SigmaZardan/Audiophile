package com.bibek.audiophile.app;

import android.app.Application;

import com.bibek.audiophile.database.AppDatabase;
import com.bibek.audiophile.database.Dao;
import com.bibek.audiophile.database.PlaylistDao;
import com.bibek.audiophile.database.SongDao;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static AppDatabase db;
    public static SongDao songDao;
    public static PlaylistDao playlistDao;
    public static Dao dao;

    // channel ids
    public static String CHANNEL_ID_1 = "CHANNEL_1";
    public static String CHANNEL_ID_2 = "CHANNEL_2";
    public static String ACTION_NEXT = "NEXT";
    public static String ACTION_PREV= "PREV";
    public static String ACTION_PLAY = "PLAY";

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        createNotificationChannel();
    }

    private void initDB() {
        db = AppDatabase.getInstance(getApplicationContext());
        songDao = db.songDao();
        playlistDao = db.playlistDao();
        dao = db.dao();
    }
    // create notification channel


    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {

            NotificationChannel notificationChannel1  = new NotificationChannel(CHANNEL_ID_1,
                    "Channel(1)", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("Channel 1 Description");

            NotificationChannel notificationChannel2  = new NotificationChannel(CHANNEL_ID_2,
                    "Channel(2)", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel2.setDescription("Channel 2 Description");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel1);
            notificationManager.createNotificationChannel(notificationChannel2);
        }
    }
}



