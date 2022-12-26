package com.bibek.audiophile.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.PlaylistSongCrossReference;
import com.bibek.audiophile.model.PlaylistWithSongs;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.ui.firstScreenActivity.FirstScreenActivity;
import com.bibek.audiophile.ui.musicPlayerActivity.MusicPlayerActivity;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddToPlaylistAdapter extends RecyclerView.Adapter<AddToPlaylistAdapter.AddToPlaylistViewHolder>{

    private Context context;
    private ArrayList<SongModel> songModelArrayList;
    int playlistId;




    public AddToPlaylistAdapter(Context context, ArrayList<SongModel> songModelArrayList,int playlistId) {
        this.context = context;
        this.songModelArrayList = songModelArrayList;
        this.playlistId = playlistId;
    }

    @NonNull
    @Override
    public AddToPlaylistAdapter.AddToPlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_add_to_playlist,parent, false);
        return new AddToPlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToPlaylistAdapter.AddToPlaylistViewHolder holder, int position) {
        SongModel songModel = songModelArrayList.get(position);
        holder.tvSongTitle.setText(songModel.getTitle());
        int currentPosition = position;
        holder.tvArtistName.setText(songModel.getArtist());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        holder.ivAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if the song is already added to the playlist

                boolean isSongExists = false;

                // check if the songs already exists in the playlist
                for(PlaylistWithSongs playlistWithSongs : App.db.dao().getPlaylistWithSongs(playlistId)){
                    for(SongModel song : playlistWithSongs.songs) {
                        isSongExists = (song.getSongId() == songModelArrayList.get(currentPosition).getSongId());
                    }
                }

                if(isSongExists) {
                    Snackbar.make(view, "SONG ALREADY ADDED TO THE PLAYLIST", Snackbar.LENGTH_LONG).show();
                }
                else {
                    addSongsToPlaylist(currentPosition, view);

                }

            }
        });

    }


    // add songs to playlist
    private void addSongsToPlaylist(int position, View view) {
        PlaylistSongCrossReference playlistSongCrossReference = new PlaylistSongCrossReference();
        playlistSongCrossReference.setPlaylistId(playlistId);
        playlistSongCrossReference.setSongId(songModelArrayList.get(position).getSongId());

        App.db.dao().insertPlaylistSongCrossReference(playlistSongCrossReference);

        Snackbar.make(view, "SONG ADDED TO THE PLAYLIST", Snackbar.LENGTH_LONG).show();

    }



    @Override
    public int getItemCount() {
        return songModelArrayList.size();
    }

    public class AddToPlaylistViewHolder  extends RecyclerView.ViewHolder{

        private TextView tvSongTitle;
        private TextView tvArtistName;
        private ImageView ivAddToPlaylist;
        public AddToPlaylistViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSongTitle = itemView.findViewById(R.id.tvSongTitleForSongsInPlaylist );
            tvArtistName = itemView.findViewById(R.id.tvArtistNameForSongsInPlaylist);
            ivAddToPlaylist  = itemView.findViewById(R.id.ivAddToPlaylist);
        }

    }
}
