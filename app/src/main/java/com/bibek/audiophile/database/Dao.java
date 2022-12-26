package com.bibek.audiophile.database;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.bibek.audiophile.model.PlaylistModel;
import com.bibek.audiophile.model.PlaylistSongCrossReference;
import com.bibek.audiophile.model.PlaylistWithSongs;
import com.bibek.audiophile.model.SongModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {


    @Insert
    void insertSong(SongModel songModel);

    @Delete
    void deleteSong(SongModel songModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaylist(PlaylistModel playlistModel);

    @Query("DELETE FROM PlaylistModel WHERE playlistId=:playlistId")
    void deletePlaylist(int playlistId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaylistSongCrossReference(PlaylistSongCrossReference ref);

    @Query("SELECT * FROM PlaylistModel")
    List<PlaylistModel>  getAllPlaylist();



    @Transaction
    @Query("SELECT * FROM PlaylistModel")
    List<PlaylistWithSongs> getPlaylistWithSongs();

    @Transaction
    @Query("SELECT * FROM PlaylistModel WHERE playlistId = :playlistId")
    List<PlaylistWithSongs> getPlaylistWithSongs(int playlistId);

    @Query("DELETE FROM PlaylistSongCrossReference WHERE playlistId = :playlistId AND songId = :songId")
    void deleteSongs(int playlistId , int songId);

    @Query("SELECT COUNT(*) FROM PlaylistSongCrossReference WHERE songId = :songId AND playlistId=:playlistId")
    int countBysSongId(int songId, int playlistId);






}
