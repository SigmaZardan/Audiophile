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

public class RemoveFromPlaylistAdapter extends RecyclerView.Adapter<RemoveFromPlaylistAdapter.RemoveFromPlaylistViewHolder>{

    private Context context;
    private ArrayList<SongModel> songModelArrayList;



    public RemoveFromPlaylistAdapter(Context context, ArrayList<SongModel> songModelArrayList) {
        this.context = context;
        this.songModelArrayList = songModelArrayList;
    }

    @NonNull
    @Override
    public RemoveFromPlaylistAdapter.RemoveFromPlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_remove_from_playlist,parent, false);
        return new RemoveFromPlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemoveFromPlaylistAdapter.RemoveFromPlaylistViewHolder holder, int position) {
        SongModel songModel = songModelArrayList.get(position);
        holder.tvSongTitle.setText(songModel.getTitle());
        holder.tvArtistName.setText(songModel.getArtist());

    }



    @Override
    public int getItemCount() {
        return songModelArrayList.size();
    }

    public class RemoveFromPlaylistViewHolder  extends RecyclerView.ViewHolder{

        private TextView tvSongTitle;
        private TextView tvArtistName;
        public RemoveFromPlaylistViewHolder (@NonNull View itemView) {
            super(itemView);

            tvSongTitle = itemView.findViewById(R.id.tvSongTitleForSongsInRemoveList );
            tvArtistName = itemView.findViewById(R.id.tvArtistNameForSongsInRemoveList);
        }

    }
}
