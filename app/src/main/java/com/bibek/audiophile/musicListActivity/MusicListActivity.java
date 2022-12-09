package com.bibek.audiophile.musicListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bibek.audiophile.R;
import com.bibek.audiophile.adapter.SongAdapter;
import com.bibek.audiophile.model.SongModel;
import com.bibek.audiophile.singletonclass.SongMediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MusicListActivity extends AppCompatActivity {

    private final static int PERMISSION_REQUEST_CODE = 200; // we can give aany value as request code
    private final static String permission = Manifest.permission.READ_EXTERNAL_STORAGE;//// here  the permission is android.permission.READ_EXTERNAL_STORAGE
    // which is a string in Manifest file
    private final Context context = MusicListActivity.this;
    ArrayList<SongModel> songModelArrayList = new ArrayList<>();

    private RecyclerView rvSongs;
    private TextView tvNoSongs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        rvSongs = findViewById(R.id.rvSongs);
        tvNoSongs = findViewById(R.id.tvNoSongs);





        // if the permission is now allowed ask for the permission and return
        if(!isPermissionAllowed()){
            requestPermissions();
            return;
        }


        Cursor cursor = readSongs();

       ArrayList<SongModel> songModelArrayList = addSongsToList(cursor);

       renderSongs(songModelArrayList);




    }

    // add the songs from the external to the  list of songs
     private ArrayList<SongModel> addSongsToList(Cursor cursor) {
        while(cursor.moveToNext()){
            // create object of the song model class by adding the data from the projection using cursor object
            SongModel songData = new SongModel(
                    cursor.getString(2),
                    cursor.getString(0),
                    cursor.getString(3),
                    cursor.getString(1));

            // check if the song exists in the database
            if(new File(songData.getPath()).exists()){
                songModelArrayList.add(songData);
            }
        }
        return songModelArrayList;
     }


     // render the songs in the music list UI
    private void renderSongs(ArrayList<SongModel> songModelArrayList)  {

        if(songModelArrayList.size() == 0 ) {
            tvNoSongs.setVisibility(View.VISIBLE);

        }
        else {
            // use the recycler view to render songs

            SongAdapter songAdapter = new SongAdapter(context , songModelArrayList);
            rvSongs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
            rvSongs.setAdapter(songAdapter);

        }

    }


    // read the songs from the external storage
    private Cursor readSongs() {
        // uri maps to the table for songs
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // projection is an array of columns that should be included for each row retrieved.
        // for each row what are the data that should be retrieved
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION


        };
        // this has the criteria for selecting rows
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";


        //access the songs and their locales from the external storage

        Cursor cursor = getContentResolver().query(uri, projection, selection , null, null );
        return cursor;

    }



    // checking whether the permission for the external storage is allowed or not

    private boolean isPermissionAllowed() {
        Log.d("Checking permission ", permission);
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),permission);
        return result == PackageManager.PERMISSION_GRANTED; // if permission granted return true otherwise false

    }


    private void  requestPermissions(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(MusicListActivity.this, permission)){
            Toast.makeText(MusicListActivity.this, "READ PERMISSION IS REQUIRED , PLEASE ALLOW FROM THE SETTINGS",Toast.LENGTH_LONG).show();

        }
        ActivityCompat.requestPermissions(MusicListActivity.this,new String[]{permission},PERMISSION_REQUEST_CODE );

    }

}