package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.AddToPlaylistAdapter;
import com.bibek.audiophile.adapter.SongAdapter;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.SongModel;

import java.util.ArrayList;

public class AddToPlaylistActivity extends AppCompatActivity {


    private RecyclerView rvAddToPlaylist;
    private ArrayList<SongModel> songModelArrayList;
    private AddToPlaylistAdapter addToPlaylistAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_playlist);



        songModelArrayList = (ArrayList<SongModel>) App.db.songDao().getAllSongs();

        rvAddToPlaylist = findViewById(R.id.rvAddToPlaylist);


        renderSongs(songModelArrayList);




    }

    private void renderSongs(ArrayList<SongModel> songModelArrayList) {


        addToPlaylistAdapter = new AddToPlaylistAdapter(AddToPlaylistActivity.this , songModelArrayList);
        rvAddToPlaylist.setLayoutManager(new LinearLayoutManager(AddToPlaylistActivity.this, LinearLayoutManager.VERTICAL,false));
        rvAddToPlaylist.setAdapter(addToPlaylistAdapter);
    }
}