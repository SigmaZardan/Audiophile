package com.bibek.audiophile.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.ui.firstScreenActivity.FirstScreenActivity;
import com.bibek.audiophile.ui.musicPlayerActivity.MusicPlayerActivity;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PlaylistViewAdapter extends RecyclerView.Adapter<PlaylistViewAdapter.PlaylistViewHolder>{

    private Context context;
    private ArrayList<SongModel> songModelArrayList;




    public PlaylistViewAdapter(Context context, ArrayList<SongModel> songModelArrayList) {
        this.context = context;
        this.songModelArrayList = songModelArrayList;

    }


    @NonNull
    @Override
    public PlaylistViewAdapter.PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_song,parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewAdapter.PlaylistViewHolder holder, int position) {
        SongModel song = songModelArrayList.get(position);
        int currentSongPosition = position;
        holder.tvSongTitle.setText(song.getTitle());
        holder.tvArtistName.setText(song.getArtist());


    }

    @Override
    public int getItemCount() {
        return songModelArrayList.size();
    }

    public void update(ArrayList<SongModel> updatedList) {
        songModelArrayList.clear();
        songModelArrayList.addAll(updatedList);
        this.notifyDataSetChanged();
    }

    public class PlaylistViewHolder  extends RecyclerView.ViewHolder{

        private TextView tvSongTitle;
        private TextView tvArtistName;
        private TextView tvNoSongsInPlaylist;


        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
        }
    }
}
