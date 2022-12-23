package com.bibek.audiophile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.app.App;
import com.bibek.audiophile.model.PlaylistModel;
import com.bibek.audiophile.ui.firstScreenActivity.FirstScreenActivity;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PlaylistViewAdapter extends RecyclerView.Adapter<PlaylistViewAdapter.PlayListViewHolder>{
    private Context context;
    private ArrayList<PlaylistModel> playlistArrayList;

    public PlaylistViewAdapter(Context context, ArrayList<PlaylistModel> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public PlaylistViewAdapter.PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.playlist_view, parent, false);
        return  new PlayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewAdapter.PlayListViewHolder holder, int position) {
        holder.tvPlaylistName.setText(playlistArrayList.get(position).getPlaylistName());
        holder.tvPlaylistName.setSelected(true);
        int currentPosition = position;


        holder.ibDeletePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromPlaylist(currentPosition, view);
            }
        });



    }

    // remove playlist
    private void removeFromPlaylist(int position, View view) {

        if(playlistArrayList.size() == 1) {
            App.db.playlistDao().delete(playlistArrayList.get(position));
            playlistArrayList.remove(position);
            Intent intent = new Intent(context, FirstScreenActivity.class);
            context.startActivity(intent);

        }
        else {
            App.db.playlistDao().delete(playlistArrayList.get(position));
            playlistArrayList.remove(position);
            this.notifyDataSetChanged();
            Snackbar.make(view, "SONG REMOVED FROM FAVORITES", Snackbar.LENGTH_LONG).show();

        }


    }



    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class PlayListViewHolder  extends RecyclerView.ViewHolder{

       private ShapeableImageView ivPlaylistImage;
        private TextView tvPlaylistName;
        private ImageButton ibDeletePlaylist;


        public PlayListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPlaylistImage = itemView.findViewById(R.id.ivPlaylistImage);
            tvPlaylistName = itemView.findViewById(R.id.tvPlaylistName);
            ibDeletePlaylist = itemView.findViewById(R.id.ibDeletePlaylist);
        }
    }


    public void update(ArrayList<PlaylistModel> updatedPlaylist){
        playlistArrayList.clear();
        playlistArrayList.addAll(updatedPlaylist);
        this.notifyDataSetChanged();
    }
}
