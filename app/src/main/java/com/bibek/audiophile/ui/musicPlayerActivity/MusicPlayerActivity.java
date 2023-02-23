package com.bibek.audiophile.ui.musicPlayerActivity;

import static com.bibek.audiophile.app.App.ACTION_NEXT;
import static com.bibek.audiophile.app.App.ACTION_PLAY;
import static com.bibek.audiophile.app.App.ACTION_PREV;
import static com.bibek.audiophile.app.App.CHANNEL_ID_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import com.bibek.audiophile.R;
import com.bibek.audiophile.databinding.ActivityMusicPlayerBinding;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.services.MusicService;
import com.bibek.audiophile.services.NotificationReceiver;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity implements ServiceConnection {


    ActivityMusicPlayerBinding binding;

    private  ArrayList<SongModel> songModelArrayList = new ArrayList<>();

   private SongModel currentSong;

    //MediaPlayer instance
    private final MediaPlayer mediaPlayer = SongMediaPlayer.getInstance();

    // instance of Music Service
    MusicService musicService;

    //media session
    MediaSessionCompat mediaSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMusicPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // songs passed from the extra in adapter is assigned
        songModelArrayList = (ArrayList<SongModel>) getIntent().getSerializableExtra("SONGS LIST");

        setComponentsWithSongResources();

        handleTheUIComponentsOnRealTime();

        mediaSession = new MediaSessionCompat(this, "PlayerAudio");


        //handle the back button pressed
        binding.ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent , this, BIND_AUTO_CREATE);

        // open the notification for every song clicked at first
        showNotification(R.drawable.pause_song);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    private void handleTheUIComponentsOnRealTime() {
        // run on UI thread
        MusicPlayerActivity.this.runOnUiThread(() -> {
            // if the current mediaPlayer instance is not null
            if(mediaPlayer != null) {
                binding.sbMusicLength.setProgress(mediaPlayer.getCurrentPosition());
                binding.tvCurrentTime.setText(changeToMinutesAndSeconds(mediaPlayer.getCurrentPosition() + ""));



                handlePlayAndPauseButtonChange();



            }
            new Handler().postDelayed((this::handleTheUIComponentsOnRealTime), 100);
        });


        handleProgressBarPositionSeek();

        handleAutoPlaySongAfterCompletion();

    }


    // play the next song automatically after it is completed
    private void handleAutoPlaySongAfterCompletion() {
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {

            try {
                playNextSong();

            }catch(Exception e) {
                e.printStackTrace();
            }
        });
    }



    // handle the pause play button icon changes
    private void handlePlayAndPauseButtonChange() {
        if(mediaPlayer.isPlaying()){
            binding.ivPausePlay.setImageResource(R.drawable.pause_song);

        }
        else {
            binding.ivPausePlay.setImageResource(R.drawable.play_button);

        }

    }

    // handle the progress bar when the user seeks it to different position
    private void handleProgressBarPositionSeek() {

        binding.sbMusicLength.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        if(mediaPlayer != null && fromUser) {
                            mediaPlayer.seekTo(progress);
                        }




                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }



     //set the xml components with the resources from the songs list
    private void setComponentsWithSongResources() {
        if(songModelArrayList != null) {
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

    }


    //play the song
    private void playMusic() {
        // user the media player instance to play the song

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
    public void playPreviousSong() {
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

        if(!mediaPlayer.isPlaying()){
            showNotification(R.drawable.play_button);
        }
        else {
            showNotification(R.drawable.pause_song);
        }



    }

    // play the next song
    public void playNextSong() {
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

        if(!mediaPlayer.isPlaying()){
            showNotification(R.drawable.play_button);
        }
        else {
            showNotification(R.drawable.pause_song);
        }

    }
    // pause or play the song
    public void pausePlaySong() {

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            showNotification(R.drawable.play_button);


        }
        else {
            mediaPlayer.start();
            showNotification(R.drawable.pause_song);


        }


    }

    // convert the milliseconds into minutes and seconds
    private String changeToMinutesAndSeconds(String duration){
        long milliseconds = Long.parseLong(duration);
         long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TimeUnit.HOURS.toMinutes(1);
         long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % TimeUnit.MINUTES.toSeconds(1);
        return String.format(Locale.US,"%02d:%02d",minutes, seconds);

    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MusicService.MyBinder binder = (MusicService.MyBinder) iBinder;
        musicService = binder.getService();
        musicService.setCallBack(MusicPlayerActivity.this);
        Log.e("Connected",musicService + " ");


    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        musicService = null;
        Log.e("Disconnected" ,musicService + " ");
    }


    public void showNotification(int playPauseBtn) {

        Intent prevIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PREV);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this, 0, prevIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent playIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent nextIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_IMMUTABLE);



        if(songModelArrayList != null) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_2)
                    .setSmallIcon(R.drawable.ic_music_icon)
                    .setContentTitle(songModelArrayList.get(SongMediaPlayer.currentIndex).getTitle())
                    .setContentText(songModelArrayList.get(SongMediaPlayer.currentIndex).getArtist())
                    .addAction(R.drawable.previous_song, "Prev", prevPendingIntent)
                    .addAction(playPauseBtn, "Play" , playPendingIntent)
                    .addAction(R.drawable.next_song , "Next", nextPendingIntent)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setOnlyAlertOnce(true)
                    .setOngoing(true)
                    .build();


            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(0,notification);

        }







    }
}