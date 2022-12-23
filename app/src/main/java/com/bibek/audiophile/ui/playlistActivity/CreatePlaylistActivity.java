package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.PlaylistViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreatePlaylistActivity extends AppCompatActivity {

    private RecyclerView rvPlaylist;
    private TextView tvNoPlaylist;
    private ImageView ivAddPlaylist;
    private ArrayList<String> playlistArrayList =  new ArrayList<>();
    private PlaylistViewAdapter playlistViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        rvPlaylist = findViewById(R.id.rvPlaylist);
        tvNoPlaylist = findViewById(R.id.tvNoPlaylist);
        ivAddPlaylist = findViewById(R.id.ivAddPlaylist);

        // for demo

        playlistArrayList.add("Anything in the context");
        playlistArrayList.add("Tu janey na milke vi hum ma mile tumse na jane kiu");
        playlistArrayList.add("milekeyhey fasle tumse na jane kiu");
        playlistArrayList.add("Neffex Moves");
        playlistArrayList.add("Neffex Moves");
        playlistArrayList.add("Neffex Moves");
        playlistArrayList.add("Neffex Moves");
        playlistArrayList.add("Neffex Moves");
        playlistArrayList.add("Neffex Moves");



        if(playlistArrayList.size() == 0) {
            tvNoPlaylist.setVisibility(View.VISIBLE);
        }

        else {
            renderPlayList(playlistArrayList);
        }

        // handle the click on the add playlist icon
        customAlertDialogue();








    }

    private void customAlertDialogue() {
        ivAddPlaylist.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 View customAlertLayout = LayoutInflater.from(CreatePlaylistActivity.this).inflate(R.layout.add_playlist_dialogue,null);
                                                 // Create an alert builder
                                                 AlertDialog.Builder builder
                                                         = new AlertDialog.Builder(CreatePlaylistActivity.this);

                                                 builder.setView(customAlertLayout)
                                                         .setTitle("Playlist Details")
                                                         .setPositiveButton("Add"
                                                                 , new DialogInterface.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(DialogInterface dialogInterface, int which) {
                                                                         // add the playlist to the database

                                                                         // also render the playlist in the CreatePlayList adapter

                                                                     }
                                                                 });

                                                 AlertDialog alertDialog = builder.create();
                                                 alertDialog.show();


                                             }
                                         }
        );

    }

    private void renderPlayList(ArrayList<String> playlistArrayList) {

        playlistViewAdapter = new PlaylistViewAdapter(CreatePlaylistActivity.this, playlistArrayList);
        rvPlaylist.setLayoutManager(new GridLayoutManager(CreatePlaylistActivity.this, 2));
        rvPlaylist.setAdapter(playlistViewAdapter);

    }
}