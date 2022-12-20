package com.bibek.audiophile.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bibek.audiophile.model.SongModel;

import java.util.List;

@Dao
public interface SongDao {

    @Insert
    void insert(SongModel songModel);



    @Update
    void update(SongModel songModel);

    @Query("SELECT * FROM songModel")
    List<SongModel> getAllSongs();

}
