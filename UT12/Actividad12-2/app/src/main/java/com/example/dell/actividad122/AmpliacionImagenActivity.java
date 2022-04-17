package com.example.dell.actividad122;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AmpliacionImagenActivity extends AppCompatActivity {

    private ImageView ivCiudadAmpliada;
    private TextView tvNombreCiudadAmpliada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampliacion_imagen);

       ivCiudadAmpliada=(ImageView)findViewById(R.id.ivCiudadAmpliada);
       tvNombreCiudadAmpliada=(TextView)findViewById(R.id.tvNombreCiudadAmpliada);

       Bundle bundle=getIntent().getExtras();
       int datoImagen=bundle.getInt("imagen");
       ivCiudadAmpliada.setImageResource(datoImagen);
       String datoNombre=bundle.getString("nombre");
       tvNombreCiudadAmpliada.setText(datoNombre);
    }
}
