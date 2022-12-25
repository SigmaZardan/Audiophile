package com.bibek.audiophile.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bibek.audiophile.model.SongModel;

import java.util.List;

@Dao
public interface SongDao {

    @Insert
    void insert(SongModel songModel);


    @Delete
    void delete(SongModel songModel);



    @Query("UPDATE songModel SET isFavorite=:isFavorite WHERE songId= :songId")
    void update(boolean isFavorite, int songId);

    @Query("SELECT * FROM songModel")
    List<SongModel> getAllSongs();

    @Query("SELECT * FROM songModel WHERE isFavorite=:isFavorite")
    List<SongModel> getAllFavoriteSongs(boolean isFavorite);

}
