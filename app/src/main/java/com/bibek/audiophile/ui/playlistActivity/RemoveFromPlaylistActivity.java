package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.AddToPlaylistAdapter;
import com.bibek.audiophile.adapter.RemoveFromPlaylistAdapter;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.SongModel;

import java.util.ArrayList;

public class RemoveFromPlaylistActivity extends AppCompatActivity {

    private RecyclerView rvRemoveFromPlaylist;
    private ArrayList<SongModel> songModelArrayList;
    private RemoveFromPlaylistAdapter addToPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_from_playlist);

        rvRemoveFromPlaylist= findViewById(R.id.rvRemoveFromPlaylist);


        songModelArrayList = (ArrayList<SongModel>) App.db.songDao().getAllSongs();

        renderTheSongs(songModelArrayList);
    }

    private void renderTheSongs(ArrayList<SongModel> songModelArrayList) {

        addToPlaylistAdapter = new RemoveFromPlaylistAdapter(RemoveFromPlaylistActivity.this , songModelArrayList);
        rvRemoveFromPlaylist.setLayoutManager(new LinearLayoutManager(RemoveFromPlaylistActivity.this, LinearLayoutManager.VERTICAL,false));
        rvRemoveFromPlaylist.setAdapter(addToPlaylistAdapter);
    }
}