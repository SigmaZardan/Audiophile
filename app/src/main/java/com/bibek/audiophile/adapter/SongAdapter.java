package com.bibek.audiophile.adapter;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.ui.musicPlayerActivity.MusicPlayerActivity;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>  {

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
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SongModel song = songModelArrayList.get(position);
        int currentSongPosition = position;
        holder.tvSongTitle.setText(song.getTitle());
        holder.tvArtistName.setText(song.getArtist());


        holder.itemView.setOnClickListener(
                view -> {


                    // create an instance or return the existing instance of SongMediaPlayer(Singleton class)
                    //
                   //  SongMediaPlayer.getInstance().reset(); // reset  everything first

                    // set the current position of the song clicked
                    SongMediaPlayer.currentIndex = position;


                    //navigate to MusicPlayerActivity
                    Intent intent = new Intent(context, MusicPlayerActivity.class);


                    // pass the songs list as extras
                    intent.putExtra("SONGS LIST", songModelArrayList);

                    // set the flags
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                   context.startActivity(intent);


                }
        );


        //handling the options menu

        holder.ivOptionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.itemView.setActivated(false);

                //creating a pop-up menu
                PopupMenu popup = new PopupMenu(context, view);

                //inflating menu from xml resource
              popup.inflate(R.menu.song_menu);

              popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){


                            case R.id.miDelete: // handle the deletion of the song
                                deleteFile(currentSongPosition, view);
                                return true;
                        }

                        return false;
                    }
                });
            }
        });

    }


    // method to handle the file deletion
    private void deleteFile(int position, View view){


        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ,
                Long.parseLong( String.valueOf(songModelArrayList.get(position).getSongId())));

        File file = new File(songModelArrayList.get(position).getPath());
        boolean deleted = file.delete();

        if(deleted ){
            context.getContentResolver().delete(contentUri, null, null);
            App.db.songDao().delete(songModelArrayList.get(position));//also delete from the database
            songModelArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, songModelArrayList.size());
            Snackbar.make(view, "SONG DELETED!", Snackbar.LENGTH_LONG).show();
        }
        else {
            // if the song is in sd card then it will not be deleted
            Snackbar.make(view, "SONG CANNOT BE DELETED!", Snackbar.LENGTH_LONG).show();
        }


    }


    @Override
    public int getItemCount() {
        return songModelArrayList.size();
    }

    // update the items in resume

    public void update(ArrayList<SongModel> updatedList) {
        songModelArrayList.clear();
        songModelArrayList.addAll(updatedList);
        this.notifyDataSetChanged();

    }


    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSongTitle;
        private TextView tvArtistName;
        private ImageView ivOptionIcon;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
            ivOptionIcon = itemView.findViewById(R.id.ivOptionIcon);

        }
    }
}
