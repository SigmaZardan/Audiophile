package com.bibek.audiophile.adapter;

import android.annotation.SuppressLint;
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
import com.bibek.audiophile.ui.musicPlayerActivity.MusicPlayerActivity;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;

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

        View view = LayoutInflater.from(context).inflate(R.layout.row_playlist_view,parent, false);
        return new PlaylistViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewAdapter.PlaylistViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SongModel song = songModelArrayList.get(position);

        holder.tvSongTitle.setText(song.getTitle());
        holder.tvArtistName.setText(song.getArtist());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create an instance or return the existing instance of SongMediaPlayer(Singleton class)
                //
                SongMediaPlayer.getInstance().reset(); // reset  everything first

                // set the current position of the song clicked
                SongMediaPlayer.currentIndex = position;


                //navigate to MusicPlayerActivity
                Intent intent = new Intent(context, MusicPlayerActivity.class);


                // pass the songs list as extras
                intent.putExtra("SONGS LIST", songModelArrayList);

                // set the flags
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                context.startActivity(intent);

                // start the notification service as well



            }
        });


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
