package com.bibek.audiophile.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.bibek.audiophile.model.PlaylistModel;

import java.util.List;

@Dao
public interface PlaylistDao {


    @Insert
    void insert(PlaylistModel playlistModel);

    @Delete
    void delete(PlaylistModel playlistModel);

    @Query("SELECT * FROM playlistModel")
    List<PlaylistModel>  getAllPlaylist();
}
