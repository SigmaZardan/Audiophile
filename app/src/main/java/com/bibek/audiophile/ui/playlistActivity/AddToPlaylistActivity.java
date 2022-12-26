package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
    private int playlistId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_playlist);



        songModelArrayList = (ArrayList<SongModel>) App.db.songDao().getAllSongs();

        rvAddToPlaylist = findViewById(R.id.rvAddToPlaylist);





        // get the playlist id
         playlistId = (int) getIntent().getSerializableExtra("PLAYLIST ID");
         Log.d("PLAYLIST ID IN PLAYLIST", String.valueOf(playlistId));

        renderSongs(songModelArrayList);




    }

    private void renderSongs(ArrayList<SongModel> songModelArrayList) {


        addToPlaylistAdapter = new AddToPlaylistAdapter(AddToPlaylistActivity.this,songModelArrayList,playlistId);
        rvAddToPlaylist.setLayoutManager(new LinearLayoutManager(AddToPlaylistActivity.this, LinearLayoutManager.VERTICAL,false));
        rvAddToPlaylist.setAdapter(addToPlaylistAdapter);
    }
}