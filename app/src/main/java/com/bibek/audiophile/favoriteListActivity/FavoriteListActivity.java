package com.bibek.audiophile.favoriteListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.SongAdapter;
import com.bibek.audiophile.model.SongModel;

import java.util.ArrayList;

public class FavoriteListActivity extends AppCompatActivity {

    private ArrayList<SongModel> favoriteSongsList = new ArrayList<>();
    private RecyclerView rvFavoriteSongs;
    private TextView tvNoFavoriteSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);


        //instantiation of the variables
        rvFavoriteSongs = findViewById(R.id.rvFavoriteSongs);
        tvNoFavoriteSongs = findViewById(R.id.tvNoFavoriteSongs);




       // render the favorite songs

        renderFavoriteSongs(favoriteSongsList);


    }

    // render the songs in the music list UI
    private void renderFavoriteSongs(ArrayList<SongModel> songModelArrayList)  {


            if(favoriteSongsList.size() == 0 ) {
                tvNoFavoriteSongs.setVisibility(View.VISIBLE);

            }
            else {
                // use the recycler view to render songs

                SongAdapter songAdapter = new SongAdapter(FavoriteListActivity.this , songModelArrayList);
                rvFavoriteSongs.setLayoutManager(new LinearLayoutManager(FavoriteListActivity.this, LinearLayoutManager.VERTICAL,false));
                rvFavoriteSongs.setAdapter(songAdapter);

            }





    }

}