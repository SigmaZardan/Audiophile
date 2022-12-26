package com.bibek.audiophile.ui.playlistActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.FavoriteSongAdapter;
import com.bibek.audiophile.adapter.PlaylistViewAdapter;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.PlaylistWithSongs;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.ui.favoriteListActivity.FavoriteListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class PlaylistInfoActivity extends AppCompatActivity {

    private RecyclerView rvPlaylistSongs;
    private ArrayList<SongModel> songModelArrayList = new ArrayList<>();
    private TextView tvNoSongsInPlaylist;
    private BottomNavigationView bottomNavigationView;
    private PlaylistViewAdapter playlistViewAdapter;
    private int playlistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_view);


        bottomNavigationView = findViewById(R.id.bottomNavigation);


        // getting the playlist id from create playlist activity
         playlistId = (int) getIntent().getSerializableExtra("PLAYLIST ID");
        Log.d("PLAYLIST ID IN THE", String.valueOf(playlistId));


        rvPlaylistSongs = findViewById(R.id.rvPlaylistSongs);

        tvNoSongsInPlaylist = findViewById(R.id.tvNoSongsInPlaylist);



        List<PlaylistWithSongs> playlistWithSongs = App.db.dao().getPlaylistWithSongs(playlistId);

        for(PlaylistWithSongs playlistSongs: playlistWithSongs ) {
           songModelArrayList.addAll(playlistSongs.songs) ;
        }

        Log.d("SONG LIST SIZE", String.valueOf(songModelArrayList.size()));



        // setting the click listener on bottom navigation

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.miAddToPlaylist:  intent =  new Intent(PlaylistInfoActivity.this, AddToPlaylistActivity.class);
                                                    intent.putExtra("PLAYLIST ID",playlistId);
                                                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                     startActivity(intent);
                                                     return true;


                    case R.id.miRemoveFromPlaylist:  intent =  new Intent(PlaylistInfoActivity.this, RemoveFromPlaylistActivity.class);
                        intent.putExtra("PLAYLIST ID",playlistId);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return true;

                }
                return false;
            }
        });

        renderSongs(songModelArrayList);



    }

    private void renderSongs(ArrayList<SongModel> songModelArrayList) {
        // set the adapter
        playlistViewAdapter = new PlaylistViewAdapter(PlaylistInfoActivity.this ,songModelArrayList);
        rvPlaylistSongs.setLayoutManager(new LinearLayoutManager(PlaylistInfoActivity.this, LinearLayoutManager.VERTICAL,false));
        rvPlaylistSongs.setAdapter(playlistViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<SongModel> updatedList = new ArrayList<>();
        for(PlaylistWithSongs playlistWithSongs : App.db.dao().getPlaylistWithSongs(playlistId)){
            updatedList.addAll(playlistWithSongs.songs);
        }
        playlistViewAdapter.update(updatedList);

    }
}