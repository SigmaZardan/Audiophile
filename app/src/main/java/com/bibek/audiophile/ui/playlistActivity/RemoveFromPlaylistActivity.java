package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.AddToPlaylistAdapter;
import com.bibek.audiophile.adapter.RemoveFromPlaylistAdapter;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.PlaylistWithSongs;
import com.bibek.audiophile.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class RemoveFromPlaylistActivity extends AppCompatActivity {

    private RecyclerView rvRemoveFromPlaylist;
    private ArrayList<SongModel> songModelArrayList  = new ArrayList<>();
    private RemoveFromPlaylistAdapter addToPlaylistAdapter;
    private int playlistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_from_playlist);

        rvRemoveFromPlaylist= findViewById(R.id.rvRemoveFromPlaylist);


        // getting the playlist id

        playlistId = (int)getIntent().getSerializableExtra("PLAYLIST ID");


        // get the songs from the current playlist and add it to the list
        List<PlaylistWithSongs> playlistWithSongs = App.db.dao().getPlaylistWithSongs(playlistId);

        for(PlaylistWithSongs playlistSongs: playlistWithSongs ) {
            songModelArrayList.addAll(playlistSongs.songs) ;
        }

        renderTheSongs(songModelArrayList);
    }

    private void renderTheSongs(ArrayList<SongModel> songModelArrayList) {

        addToPlaylistAdapter = new RemoveFromPlaylistAdapter(RemoveFromPlaylistActivity.this , songModelArrayList, playlistId);
        rvRemoveFromPlaylist.setLayoutManager(new LinearLayoutManager(RemoveFromPlaylistActivity.this, LinearLayoutManager.VERTICAL,false));
        rvRemoveFromPlaylist.setAdapter(addToPlaylistAdapter);
    }
}