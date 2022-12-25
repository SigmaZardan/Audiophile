package com.bibek.audiophile.ui.playlistActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PlaylistInfoActivity extends AppCompatActivity {

    private RecyclerView rvPlaylistSongs;

    private TextView tvNoSongsInPlaylist;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_view);


        bottomNavigationView = findViewById(R.id.bottomNavigation);



        // setting the click listener on bottom navigation

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.miAddToPlaylist:  intent =  new Intent(PlaylistInfoActivity.this, AddToPlaylistActivity.class);
                                                     startActivity(intent);
                                                     return true;


                    case R.id.miRemoveFromPlaylist:  intent =  new Intent(PlaylistInfoActivity.this, RemoveFromPlaylistActivity.class);
                        startActivity(intent);
                        return true;

                }
                return false;
            }
        });




    }
}