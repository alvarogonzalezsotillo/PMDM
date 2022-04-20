package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConexionVolleyActivity extends AppCompatActivity {

    private static String TAG = "ConexionVolleyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_volley);

        TextView tv = findViewById(R.id.urlMapaText);
        ImageView iv = findViewById(R.id.mapaImage);

        findViewById(R.id.getMapaButton).setOnClickListener( (View v ) -> {

            // PARA EJECUTAR EN EL THREAD DE LA INTERFAZ
            Handler handler = new Handler(Looper.getMainLooper());

            tv.setText("Consultando URL...");
            CompletableFuture<String> cf = EjemploConexionVolley.getURLMapa(ConexionVolleyActivity.this);
            cf.exceptionally( (Throwable t) -> {
                handler.post( ()-> tv.setText("Error con volley:" + t ) );
                Log.d(TAG,"Error con volley", t );
                return null;
            }).thenApplyAsync( (String url)-> {
                tv.setText("La URL es " + url + ". Cargando imagen con Picasso....");

                Picasso.get().load(url).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG,"Imagen cargada");
                        tv.setText("Imagen cargada con éxito");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG,"Imagen con error",e);
                        tv.setText("Error en la carga de la imagen:" + e);
                    }
                });
                return url;
            }, handler::post );
            // CON HANDLER::POST, EL THENAPPLYASYNC SE EJECUTA EN EL THREAD DE LA INTERFAZ
        } );
    }
}