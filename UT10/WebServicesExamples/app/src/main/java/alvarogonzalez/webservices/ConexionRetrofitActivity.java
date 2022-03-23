package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        findViewById(R.id.getMunicipiosButton).setOnClickListener( (View v) -> {
            Log.d(TAG,"Pidiendo municipios");
            tv.setText( "Pidiendo municipios...");
            CompletableFuture<Municipio[]> cf = EjemploConexionRetrofit.getMunicipios();
            cf.exceptionally( (Throwable t) -> {
                Log.d(TAG,"Error", t );
                tv.setText("Error:" + t );
                return null;
            })
            .thenRun( () -> {

                Municipio[] ms = cf.getNow(null);
                tv.setText( Arrays.asList(ms).toString() );

                Log.d(TAG,"Consegido:" + Arrays.asList(ms).toString() );

                lv.setAdapter( new MunicipioAdapter(ms) );
            });
        });
    }
}