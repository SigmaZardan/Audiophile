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

public class FavoriteSongAdapter extends RecyclerView.Adapter<FavoriteSongAdapter.FavoriteSongViewHolder>{

    private Context context;
    private ArrayList<SongModel> favoriteSongArrayList;



    public FavoriteSongAdapter(Context context, ArrayList<SongModel> favoriteSongArrayList) {
        this.context = context;
        this.favoriteSongArrayList = favoriteSongArrayList;
    }

    @NonNull
    @Override
    public FavoriteSongAdapter.FavoriteSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_song_favorite,parent, false);

        return new FavoriteSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteSongAdapter.FavoriteSongViewHolder holder, int position) {

        SongModel songModel = favoriteSongArrayList.get(position);
        int currentPosition = position;
        holder.tvSongTitle.setText(songModel.getTitle());
        holder.tvArtistName.setText(songModel.getArtist());

        holder.itemView.setOnClickListener(
                view -> {


                    // create an instance or return the existing instance of SongMediaPlayer(Singleton class)
                    //
                    SongMediaPlayer.getInstance().reset(); // reset  everything first

                    // set the current position of the song clicked
                    SongMediaPlayer.currentIndex = currentPosition;


                    //navigate to MusicPlayerActivity
                    Intent intent = new Intent(context, MusicPlayerActivity.class);


                    // pass the songs list as extras
                    intent.putExtra("SONGS LIST", favoriteSongArrayList);

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
                popup.inflate(R.menu.favorite_song_menu);

                popup.show();



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.miRemoveFromFavoriteList: // handle the song addition to favorite list
                                    removeFromFavoriteList(currentPosition, view);
                                    return true;

                        }

                        return false;
                    }
                });
            }
        });

    }


    //method to remove the song from favorite list

    private void removeFromFavoriteList(int position, View view) {
        if(favoriteSongArrayList.size() == 1) {

            App.db.songDao().update(false,favoriteSongArrayList.get(position).getSongId());
            favoriteSongArrayList.remove(position);
            Intent  intent = new Intent(context, FirstScreenActivity.class);
            context.startActivity(intent);

        }
        else {
            App.db.songDao().update(false,favoriteSongArrayList.get(position).getSongId());
            favoriteSongArrayList.remove(position);
            notifyDataSetChanged();
            Snackbar.make(view, "SONG REMOVED FROM FAVORITES", Snackbar.LENGTH_LONG).show();

        }




    }




    @Override
    public int getItemCount() {
        return favoriteSongArrayList.size();
    }

    public class FavoriteSongViewHolder  extends RecyclerView.ViewHolder{
        private TextView tvSongTitle;
        private TextView tvArtistName;
        private ImageView ivOptionIcon;
        public FavoriteSongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
            ivOptionIcon = itemView.findViewById(R.id.ivOptionIcon);
        }

    }
}
