package com.bibek.audiophile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.musicPlayerActivity.MusicPlayerActivity;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

    private Context context;
    private ArrayList<SongModel> songModelArrayList;

    public SongAdapter(Context context, ArrayList<SongModel> songModelArrayList) {
        this.context = context;
        this.songModelArrayList = songModelArrayList;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        SongModel song = songModelArrayList.get(position);
        int currentSongPosition = position;
        holder.tvSongTitle.setText(song.getTitle());
        holder.tvArtistName.setText(song.getArtist());

        holder.itemView.setOnClickListener(
                view -> {


                    // create an instance or return the existing instance of SongMediaPlayer(Singleton class)
                    //
                    SongMediaPlayer.getInstance().reset(); // reset  everything first

                    // set the current position of the song clicked
                    SongMediaPlayer.currentIndex = currentSongPosition;

                    //navigate to MusicPlayerActivity
                    Intent intent = new Intent(context, MusicPlayerActivity.class);

                    // pass the songs list as extras
                    intent.putExtra("SONGS LIST", songModelArrayList);

                    // set the flags
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                   context.startActivity(intent);


                }
        );

    }

    @Override
    public int getItemCount() {
        return songModelArrayList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSongTitle;
        private TextView tvArtistName;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);


        }
    }
}
