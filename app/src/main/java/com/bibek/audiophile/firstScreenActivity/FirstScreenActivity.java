package com.bibek.audiophile.firstScreenActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.FirstScreenItemAdapter;
import com.bibek.audiophile.adapter.RecyclerViewInterface;
import com.bibek.audiophile.favoriteListActivity.FavoriteListActivity;
import com.bibek.audiophile.model.FirstScreenItemModel;
import com.bibek.audiophile.musicListActivity.MusicListActivity;
import com.bibek.audiophile.playlistActivity.CreatePlaylistActivity;

import java.util.ArrayList;

public class FirstScreenActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView rvFirstScreenItems;
    private FirstScreenItemAdapter adapter;
    private ArrayList<FirstScreenItemModel> firstScreenItemModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


        rvFirstScreenItems = findViewById(R.id.rvFirstScreenItems);

        // adding the hardcoded three items on the first screen as needed
        FirstScreenItemModel item1 = new FirstScreenItemModel();
        item1.setFirstScreenItemIconId(R.drawable.music_icon);
        item1.setFirstScreenItemTitle("Songs ");
        item1.setFirstScreenArrowIcon(R.drawable.next_icon);


        FirstScreenItemModel item2 = new FirstScreenItemModel();
        item2.setFirstScreenItemIconId(R.drawable.favorite_icon);
        item2.setFirstScreenItemTitle("Favorite");
        item2.setFirstScreenArrowIcon(R.drawable.next_icon);


        FirstScreenItemModel item3 = new FirstScreenItemModel();
        item3.setFirstScreenItemIconId(R.drawable.playlist_icon);
        item3.setFirstScreenItemTitle("Playlist");
        item3.setFirstScreenArrowIcon(R.drawable.next_icon);


        firstScreenItemModelArrayList.add(item1);
        firstScreenItemModelArrayList.add(item2);
        firstScreenItemModelArrayList.add(item3);


        // setting up the adapter
        adapter  = new FirstScreenItemAdapter( this , FirstScreenActivity.this,firstScreenItemModelArrayList );
        rvFirstScreenItems.setLayoutManager(new LinearLayoutManager(FirstScreenActivity.this, LinearLayoutManager.VERTICAL,false));
        rvFirstScreenItems.setAdapter(adapter);




    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent;
        switch (position)  {
            case 0 : intent = new Intent(getApplicationContext(), MusicListActivity.class);
                     startActivity(intent);

            case 1 : intent = new Intent(getApplicationContext(), FavoriteListActivity.class);
                startActivity(intent);

            case 2 : intent = new Intent(getApplicationContext(), CreatePlaylistActivity.class);
                startActivity(intent);

        }

    }


}
