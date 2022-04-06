package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import alvarogonzalez.webservices.EjemploConexionRetrofit.Municipio;

public class ConexionRetrofitActivity extends AppCompatActivity {

    private static String TAG = "ConexionRetrofitActivity";


    private class MunicipioAdapter extends BaseAdapter{

        private Municipio[] municipios;

        public MunicipioAdapter(Municipio[] municipios ){
            this.municipios = municipios;
        }

        @Override
        public int getCount() {
            return municipios.length;
        }

        @Override
        public Municipio getItem(int position) {
            return municipios[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if( convertView == null ){
                LayoutInflater layoutInflater=LayoutInflater.from(ConexionRetrofitActivity.this);
                convertView = layoutInflater.inflate(R.layout.layout_municipio, null);
            }
            Municipio m = getItem(position);
            ((TextView)convertView.findViewById(R.id.idText)).setText(m.id);
            ((TextView)convertView.findViewById(R.id.nombreText)).setText(m.nombre);
            ((TextView)convertView.findViewById(R.id.longitudText)).setText(m.longitud);
            ((TextView)convertView.findViewById(R.id.latitudText)).setText(m.latitud);

            return convertView;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_retrofit);

        TextView tv = findViewById(R.id.resultadoRetrofitText);
        ListView lv = findViewById(R.id.municipiosList);
        tv.setMovementMethod(new ScrollingMovementMethod());

        // UN HANDLER ES CAPAZ DE EJECUTAR COSAS EN EL THREAD DE LA INTERFAZ,
        // AUNQUE SE LLAME DESDE OTRO THREAD
        Handler handler = new Handler( Looper.getMainLooper() );

        findViewById(R.id.getMunicipiosButton).setOnClickListener( (View v) -> {
            Log.d(TAG,"Pidiendo municipios");
            tv.setText( "Pidiendo municipios...");
            CompletableFuture<Municipio[]> cf = EjemploConexionRetrofit.getMunicipios();

            // SE VIGILA SI EL FUTURO ACABA CON ERROR (EXCEPTIONALLY) O CORRECTAMENTE (THENAPPLYASYNC)
            cf.exceptionally( (Throwable t) -> {
                Log.d(TAG,"Error", t );
                handler.post( ()-> tv.setText("Error:" + t ) );
                return null;
            })
            .thenApplyAsync( (Municipio[] ms) -> {
                tv.setText( Arrays.asList(ms).toString() );
                Log.d(TAG,"Consegido:" + Arrays.asList(ms).toString() );
                lv.setAdapter( new MunicipioAdapter(ms) );
                return ms;
            }, handler::post );
            // SE USA HANDLER::POST COMO EXECUTOR, PARA QUE EL CODIGO SE EJECUTE EN EL THREAD DE LA INTERFAZ
        });
    }
}