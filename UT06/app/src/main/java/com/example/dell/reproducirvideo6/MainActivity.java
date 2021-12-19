package com.example.dell.reproducirvideo6;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";


    //Para apartado A)
    String videoURL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            VideoView videoView = (VideoView) findViewById(R.id.videoView);
            Map<String, String> headers = new HashMap<>();
            //headers.put("Cache-control", "no-cache");
            //Log.d( TAG, headers.toString() );
            videoView.setVideoURI( getVideoUri(), headers );
            videoView.start();
            Log.d( TAG, "He iniciado el video ");

        }catch (Exception e){
            Log.e(TAG," videoView error ", e );
        }

    }



    private Uri getVideoUri(){
        String rutaVideo = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        Log.d(TAG, "rutaVideo:" + rutaVideo );
        return Uri.parse(rutaVideo);
    }

}


