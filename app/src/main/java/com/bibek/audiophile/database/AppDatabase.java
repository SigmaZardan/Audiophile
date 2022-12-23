package com.bibek.audiophile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bibek.audiophile.model.PlaylistModel;
import com.bibek.audiophile.model.SongModel;

@Database(entities = {SongModel.class, PlaylistModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "songbase";

    private static AppDatabase instance;

    public abstract SongDao songDao();

    public abstract PlaylistDao playlistDao();

    public static AppDatabase getInstance(Context context) {

        if(instance == null ) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext() , AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;


    }



}
