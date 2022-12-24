package com.bibek.audiophile.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class SongModel implements Serializable {

    private String path;
    private String title;
    private String duration;
    private String artist;
    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;
    private boolean isFavorite;



    public SongModel(String path, String title, String duration, String artist,int id , boolean isFavorite) {
        this.path = path;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.id = id;
        this.isFavorite = isFavorite;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
