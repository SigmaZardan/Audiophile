package com.bibek.audiophile.musicPlayerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bibek.audiophile.R;
import com.bibek.audiophile.databinding.ActivityMusicPlayerBinding;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {


    ActivityMusicPlayerBinding binding;

    private  ArrayList<SongModel> songModelArrayList;

   private SongModel currentSong;

    //MediaPlayer instance
    private MediaPlayer mediaPlayer = SongMediaPlayer.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // songs passed from the extra in adapter is assigned
        songModelArrayList = (ArrayList<SongModel>) getIntent().getSerializableExtra("SONGS LIST");

        setComponentsWithSongResources();
    }

     //set the xml components with the resources from the songs list
    private void setComponentsWithSongResources() {
        currentSong = songModelArrayList.get(SongMediaPlayer.currentIndex);
        binding.tvMusicTitle.setText(currentSong.getTitle());
        binding.tvMusicTitle.setSelected(true); // start marquee property
        binding.tvArtistName.setText(currentSong.getArtist());
        binding.tvTotalTime.setText(changeToMinutesAndSeconds(currentSong.getDuration()));

        binding.ivPausePlay.setOnClickListener(view -> pausePlaySong());
        binding.ivPreviousSong.setOnClickListener(view -> playPreviousSong());
        binding.ivNextSong.setOnClickListener(view -> playNextSong());

        playMusic();

    }


    //play the song
    private void playMusic() {
        // we need the media player instance to play the song


        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(currentSong.getPath()); // set the path of the song
            mediaPlayer.prepare(); //collects metadata about the file or stream to be played
            mediaPlayer.start();

            // handling the seekbar
            binding.sbMusicLength.setProgress(0);
            binding.sbMusicLength.setMax(mediaPlayer.getDuration());

        }catch(IOException e){
            e.printStackTrace();
        }




    }

    // play the previous song
    private void playPreviousSong() {

        // if the current song is the last song
        if(SongMediaPlayer.currentIndex == songModelArrayList.size() - 1){
            return;
        }
        //increase the current index of the song
        SongMediaPlayer.currentIndex += 1;
        // reset
        mediaPlayer.reset();

        //render the components
        setComponentsWithSongResources();



    }

    // play the next song
    private void playNextSong() {

        // if the current song is the first song
        if(SongMediaPlayer.currentIndex == 0) {
            return;
        }
        // decrease the current index
        SongMediaPlayer.currentIndex -= 1;
        // reset
        mediaPlayer.reset();

        //render the components
        setComponentsWithSongResources();

    }
    // pause or play the song
    private void pausePlaySong() {

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        else {
            mediaPlayer.start();
        }


    }

    // convert the milliseconds into minutes and seconds
    private String changeToMinutesAndSeconds(String duration){
        long milliseconds = Long.parseLong(duration);
         long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TimeUnit.HOURS.toMinutes(1);
         long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % TimeUnit.MINUTES.toSeconds(1);
        return String.format(Locale.US,"%02d:%02d",minutes, seconds);

    }
}