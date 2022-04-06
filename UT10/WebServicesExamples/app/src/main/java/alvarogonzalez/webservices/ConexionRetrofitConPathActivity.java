package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import alvarogonzalez.webservices.EjemploConexionRetrofit.RiesgoIncendio;
import static alvarogonzalez.webservices.EjemploConexionRetrofit.Zona.*;

import java.util.concurrent.CompletableFuture;

public class ConexionRetrofitConPathActivity extends AppCompatActivity {

    private static String TAG = "ConexionRetrofitConPathActivity";

    private void cargaImagen(ImageView iv, String url){
        Picasso.get().load(url).into(iv, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"Imagen cargada:" + url);
            }

            @Override
            public void onError(Exception e) {
                Log.d(TAG,"Error al cargar " + url, e );
                Toast.makeText(ConexionRetrofitConPathActivity.this, "Error al cargar " + url, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cargaImagen(ImageView iv, CompletableFuture<RiesgoIncendio> cf ){
        cf.exceptionally( (Throwable t) -> {
            Log.d(TAG,"Error al pedir datos de incencios:" + t, t );
            return null;
        }).thenRun( () -> {
            String url = cf.getNow(null).datos;
            // NO IMPORTA QUE SE EJECUTE DESDE OTRO THREAD,
            // EL PROPIO VOLLEY SE ASEGURA DE USAR EL THREAD DE LA INTERFAZ
            cargaImagen(iv,url);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conextion_retrofit_con_path);

        ImageView iv = findViewById(R.id.imageView);

        findViewById(R.id.peninsulaButton).setOnClickListener( (View v)-> {
            CompletableFuture<RiesgoIncendio> cf = EjemploConexionRetrofit.getRiesgoIncencio(PENINSULA);
            cargaImagen(iv,cf);
        });
        findViewById(R.id.canariasButton).setOnClickListener( (View v)-> {
            CompletableFuture<RiesgoIncendio> cf = EjemploConexionRetrofit.getRiesgoIncencio(CANARIAS);
            cargaImagen(iv,cf);
        });
        findViewById(R.id.balearesButton).setOnClickListener( (View v)-> {
            CompletableFuture<RiesgoIncendio> cf = EjemploConexionRetrofit.getRiesgoIncencio(BALEARES);
            cargaImagen(iv,cf);
        });

    }
}