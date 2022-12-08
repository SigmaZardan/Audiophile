package com.bibek.audiophile.musicListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bibek.audiophile.R;

public class MusicListActivity extends AppCompatActivity {

    private final static int PERMISSION_REQUEST_CODE = 200; // we can give aany value as request code
    private final static String permission = Manifest.permission.READ_EXTERNAL_STORAGE;//// here  the permission is android.permission.READ_EXTERNAL_STORAGE
    // which is a string in Manifest file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);



        // if the permission is now allowed ask for the permission and return
        if(!isPermissionAllowed()){
            requestPermissions();
            return;
        }


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