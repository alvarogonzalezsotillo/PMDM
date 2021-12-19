package com.example.dell.reproducirvideo6;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

//En este ejercicio, hemos utilizado en lugar de la clase VideoView, unas clases procedentes de una libreria
//externa llamada ExoPlayer. Hemos tenido que incorporarla a través del build.gradle (Module: app) de la manera
//habitual. Habrá bastantes clases nuevas, todas dependientes de estas librerias.
//Además hemos incorporado el recurso (esto es, el video) en un servidor web local mediante Apache con
//XAMPP, funcionando perfectamente. Las clases de exoPlayer proporcionarán un reproductor, donde los avances y retrocesos
//son por defecto de 15 segundos, por lo que interesará para videos largos. Esto será configurable

public class MainActivity extends AppCompatActivity {



    //Para apartado A)
    String videoURL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    //La siguiente sentencia indica la ruta hacia un servidor web local (Debes tenerlo arrancado y dando servicio, y además alojar en él recurso)
    //Comenta la declaración de la variable videoURL anterior y descomenta la siguiente para hacer apartado B)
    //String videoURL = "http://192.168.1.129/Videos/BodaBosquet.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleExoPlayerView exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        try {


            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }catch (Exception e){
            Log.e("MainActivity"," exoplayer error ", e );
        }

    }
}
