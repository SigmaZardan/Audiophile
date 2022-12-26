package com.bibek.audiophile.model;


import androidx.room.Entity;

@Entity(
        primaryKeys = {"playlistId", "songId"}
)
public class PlaylistSongCrossReference {

    private int playlistId;
    private int songId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
