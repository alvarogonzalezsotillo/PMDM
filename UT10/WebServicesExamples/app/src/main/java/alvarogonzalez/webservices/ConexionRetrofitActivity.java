package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ConexionRetrofitActivity extends AppCompatActivity {

    private static String TAG = "ConexionRetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_retrofit);

        TextView tv = findViewById(R.id.resultadoRetrofitText);
        tv.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.getMunicipiosButton).setOnClickListener( (View v) -> {
            Log.d(TAG,"Pidiendo municipios");
            tv.setText( "Pidiendo municipios...");
            CompletableFuture<EjemploConexionRetrofit.Municipio[]> cf = EjemploConexionRetrofit.getMunicipios();

            cf.exceptionally( (Throwable t) -> {
                Log.d(TAG,"Error", t );
                tv.setText("Error:" + t );
                return null;
            })
            .thenRun( () -> {

                EjemploConexionRetrofit.Municipio[] ms = cf.getNow(null);
                tv.setText( Arrays.asList(ms).toString() );

                Log.d(TAG,"Consegido:" + Arrays.asList(ms).toString() );
            });
        });
    }
}