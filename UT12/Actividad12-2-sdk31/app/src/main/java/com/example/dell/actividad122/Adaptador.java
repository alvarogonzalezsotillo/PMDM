package com.example.dell.actividad122;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alumnom on 24/04/2018.
 */

public class Adaptador extends BaseAdapter {
    Context contexto;
    List<Ciudad> listaCiudades;

    public Adaptador(Context contexto, List<Ciudad> listaCiudades) {
        this.contexto = contexto;
        this.listaCiudades = listaCiudades;
    }

    @Override
    public int getCount() {
        return listaCiudades.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCiudades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View view, ViewGroup viewGroup) {
        final int pos=i;
        View vista = view;
        LayoutInflater inflador = LayoutInflater.from(contexto);
        vista = inflador.inflate(R.layout.item, null);

        TextView tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
        TextView tvPais = (TextView) vista.findViewById(R.id.tvPais);

        TextView tvPoblacion = (TextView) vista.findViewById(R.id.tvPoblacion);

        ImageView ivCiudad = (ImageView) vista.findViewById(R.id.ivCiudad);
        ivCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        

        tvNombre.setText(listaCiudades.get(i).getNombre());
        tvPais.setText(listaCiudades.get(i).getPais());

        tvPoblacion.setText("Pobl: "+ listaCiudades.get(i).getPoblacion());
        ivCiudad.setImageResource(listaCiudades.get(i).getImagen());

        ImageView ivAmpliar=(ImageView)vista.findViewById(R.id.ivAmpliar);
        ivAmpliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), i+"", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(v.getContext(), AmpliacionImagenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("imagen", listaCiudades.get(i).getImagen());
                intent.putExtra("nombre", listaCiudades.get(i).getNombre());

                v.getContext().startActivity(intent);

            }
        });


        ImageView ivMapa=(ImageView)vista.findViewById(R.id.ivMapa);
        ivMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), i+"", Toast.LENGTH_LONG).show();

                //Voy a pasar como parámetros la longitud y la latitud, pero en lugar de hacerlo con un Bundle (que se puede hacer)
                //lo haré a través de las referencias a las variables de clase (es decir, en la clase MapsActivity, he creado unas
                //variables de tipo static, y por ello, puedo referirlas como variables de clase de la forma siguiente
                //Con un Bundle probablamente sea más elegante, pero ambos métodos son igualmente efectivos
                MapsActivity.latitud=Double.parseDouble(listaCiudades.get(i).getLatitud());
                MapsActivity.longitud=Double.parseDouble(listaCiudades.get(i).getLongitud());


                Intent intent=new Intent(v.getContext(), MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //Por otro lado, y por hacerlo diferente de lo que he hecho con la latitud y la longitud (hemos usado variables
                // static o de clase), pasaremos a través de un Bundle el nombre de la ciudad para ponerlo en el marcador
                intent.putExtra("nombre", listaCiudades.get(i).getNombre());

                v.getContext().startActivity(intent);
            }
        });


        return vista;
    }


}
