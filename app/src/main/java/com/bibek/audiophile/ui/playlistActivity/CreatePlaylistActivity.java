package com.bibek.audiophile.ui.playlistActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.CreatePlaylistAdapter;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.databinding.AddPlaylistDialogueBinding;
import com.bibek.audiophile.model.PlaylistModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CreatePlaylistActivity extends AppCompatActivity {

    private RecyclerView rvPlaylist;
    private TextView tvNoPlaylist;
    private ImageView ivAddPlaylist;
    private ArrayList<PlaylistModel> playlistArrayList =  new ArrayList<>();
    private CreatePlaylistAdapter playlistViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        rvPlaylist = findViewById(R.id.rvPlaylist);
        tvNoPlaylist = findViewById(R.id.tvNoPlaylist);
        ivAddPlaylist = findViewById(R.id.ivAddPlaylist);


        playlistArrayList = (ArrayList<PlaylistModel>) App.db.playlistDao().getAllPlaylist();


            renderPlayList(playlistArrayList);

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
                                                 //using binder to get the entered playlist name
                                                 AddPlaylistDialogueBinding addPlaylistDialogueBinding = AddPlaylistDialogueBinding.bind(customAlertLayout);

                                                 builder.setView(customAlertLayout)
                                                         .setTitle("Playlist Details")
                                                         .setPositiveButton("Add"
                                                                 , new DialogInterface.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(DialogInterface dialogInterface, int which) {

                                                                         tvNoPlaylist.setVisibility(View.GONE);


                                                                         // get the inputted playlist name
                                                                         String playlistName =addPlaylistDialogueBinding.tvPlaylistName.getText().toString();

                                                                         // add the playlist to the database
                                                                         addPlayListToDatabase(playlistName, view);


                                                                         // also update the playlist
                                                                         if(playlistViewAdapter!=null) {
                                                                             playlistViewAdapter.update((ArrayList<PlaylistModel>) App.db.playlistDao().getAllPlaylist());
                                                                         }



                                                                     }
                                                                 });

                                                 AlertDialog alertDialog = builder.create();
                                                 alertDialog.show();


                                             }
                                         }
        );

    }

    // render the playlist

    private void renderPlayList(ArrayList<PlaylistModel> playlistArrayList) {

        playlistViewAdapter = new CreatePlaylistAdapter(CreatePlaylistActivity.this, playlistArrayList);
        rvPlaylist.setLayoutManager(new GridLayoutManager(CreatePlaylistActivity.this, 2));
        rvPlaylist.setAdapter(playlistViewAdapter);
    }

    //check if the playlist name is empty
    private void addPlayListToDatabase(String playlistName , View view) {
        if(playlistName.equals("")){
            Snackbar.make(view, "THE NAME CANNOT BE BLANK!",Snackbar.LENGTH_LONG).show();
        }
        else {
            PlaylistModel playlistModel = new PlaylistModel();
            playlistModel.setPlaylistName(playlistName);

                App.db.dao().insertPlaylist(playlistModel);
                Log.d("PLAYLIST_TEST", String.valueOf(App.db.dao().getAllPlaylist().size()));
                Snackbar.make(view, "PLAYLIST ADDED",Snackbar.LENGTH_LONG).show();

        }
    }

}