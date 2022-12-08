package com.bibek.audiophile.model;

public class SongModel {

    private String path;
    private String title;
    private String duration;
    private String artist;


    public SongModel(String path, String title, String duration, String artist) {
        this.path = path;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
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
}
