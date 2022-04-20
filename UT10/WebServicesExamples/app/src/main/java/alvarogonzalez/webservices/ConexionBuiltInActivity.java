package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class ConexionBuiltInActivity extends AppCompatActivity {

    private static String TAG="ConexionBuiltInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_built_in);

        findViewById(R.id.buscarButton).setOnClickListener( (View v) -> {

            // EL HANDLER SE UTILIZA PARA EJECUTAR COSAS EN EL THREAD DE LA INTERFAZ
            Handler handler = new Handler(Looper.myLooper());

            TextView resultado = findViewById(R.id.resultadoText);
            resultado.setText("Buscando...");
            String busqueda = ((TextView)findViewById(R.id.queryText)).getText().toString();

            // LA CONEXIÓN DE RED DEBE HACERSE FUERA DEL THREAD PRINCIPAL
            // DE ESTA FORMA, LA INTERFAZ NO SE BLOQUEA
            // SE UTILIZA UN HANDLER PARA QUE LA ACTUALIZACIÓN DE LA INTERFAZ SEA EN EL THREAD PRINCIPAL
            new Thread( ()->{
                try {
                    long numero = EjemploConexionBuiltIn.numeroResultadosGoogle(busqueda);
                    handler.post( ()->resultado.setText( "Para '" + busqueda + "' se encuentran " + numero + " resultados.") );
                } catch (IOException e) {
                    handler.post( ()-> resultado.setText( "Para '" + busqueda + "' hay un error:" + e ) );
                    Log.d( TAG,"Error:" + e, e );
                }
            }).start();
        });
    }
}