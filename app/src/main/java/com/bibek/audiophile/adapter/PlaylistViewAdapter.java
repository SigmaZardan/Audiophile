package com.bibek.audiophile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PlaylistViewAdapter extends RecyclerView.Adapter<PlaylistViewAdapter.PlayListViewHolder>{
    private Context context;
    private ArrayList<String> playlistArrayList;

    public PlaylistViewAdapter(Context context, ArrayList<String> playlistArrayList) {
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
        holder.tvPlaylistName.setText(playlistArrayList.get(position));
        holder.tvPlaylistName.setSelected(true);



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
}
