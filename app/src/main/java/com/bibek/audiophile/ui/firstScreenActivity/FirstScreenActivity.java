package com.bibek.audiophile.ui.firstScreenActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.FirstScreenItemAdapter;
import com.bibek.audiophile.adapter.RecyclerViewInterface;
import com.bibek.audiophile.ui.favoriteListActivity.FavoriteListActivity;
import com.bibek.audiophile.model.FirstScreenItemModel;
import com.bibek.audiophile.ui.musicListActivity.MusicListActivity;
import com.bibek.audiophile.ui.playlistActivity.CreatePlaylistActivity;

import java.util.ArrayList;

public class FirstScreenActivity extends AppCompatActivity implements RecyclerViewInterface{

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


        FirstScreenItemModel item3 = new FirstScreenItemModel();
        item3.setFirstScreenItemIconId(R.drawable.playlist_icon_);
        item3.setFirstScreenItemTitle("Playlist");
        item3.setFirstScreenArrowIcon(R.drawable.next_icon);


        firstScreenItemModelArrayList.add(item1);
        firstScreenItemModelArrayList.add(item3);


        // setting up the adapter
        adapter  = new FirstScreenItemAdapter(  this, FirstScreenActivity.this,firstScreenItemModelArrayList );
        rvFirstScreenItems.setLayoutManager(new LinearLayoutManager(FirstScreenActivity.this, LinearLayoutManager.VERTICAL,false));
        rvFirstScreenItems.setAdapter(adapter);




    }




    @Override
    public void onItemClickListener(int position) {
        Log.d("POSITION", String.valueOf(position));
        Intent intent;

        switch(position) {
            case 0 :   intent = new Intent(FirstScreenActivity.this, MusicListActivity.class);
                      startActivity(intent);
                      break;

            case 1 : intent = new Intent(FirstScreenActivity.this, CreatePlaylistActivity.class);
                     startActivity(intent);
                     break;



            default: System.out.println("Just click on the items");



        }

    }
}
