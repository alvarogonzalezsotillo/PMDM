package com.example.dell.actividad122;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaCiudades;
    ArrayList<Ciudad> lista;
    Adaptador miAdaptador;

    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCiudades = findViewById(R.id.lvPaises);


        lista= new ArrayList<>();

        lista.add(new Ciudad("Bruselas", "Bélgica", "177 307", "50.8467", "4.3547", R.drawable.bruselas));
        lista.add(new Ciudad("Budapest", "Hungría", "1 752 704", "47.498333", "19.040833", R.drawable.budapest));
        lista.add(new Ciudad("Dublín", "Irlanda", "527 612", "53.3425", "-6.265833", R.drawable.dublin));
        lista.add(new Ciudad("Florencia", "Italia", "382 258", "43.771389", "11.254167", R.drawable.florencia));
        lista.add(new Ciudad("París", "Francia", "10 516 110", "48.856578", "2.351828", R.drawable.paris));
        lista.add(new Ciudad("Praga", "Chequia", "1 280 508", "50.088611", "14.421389", R.drawable.praga));
        lista.add(new Ciudad("Sevilla", "España", "689 434", "37.383333", "-5.983333", R.drawable.sevilla));
        lista.add(new Ciudad("Viena", "Austria", "1 840 573", "48.20833", "16.373064", R.drawable.viena));


        miAdaptador = new Adaptador(getApplicationContext(), lista);

        listaCiudades.setAdapter(miAdaptador);



        listaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //registerForContextMenu(view);
                posicion=position;
                Toast.makeText(getApplicationContext(), position+"", Toast.LENGTH_LONG).show();

            }
        });


        listaCiudades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                registerForContextMenu(view);
                //posicion = position; //De esta forma tengo determinado sobre que item del Listview estoy actuando
                //Toast.makeText(getApplicationContext(), position, Toast.LENGTH_LONG).show();

                return false;
            }
        });


    }

    //Métodos para MENÚ CONTEXTUAL
    //Tenemos que sobreescribir los siguientes 2 métodos para el menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Elija qué desea hacer:");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.itemAmpliarImagen:
                //ampliar(posicion);
                Toast.makeText(getApplicationContext(), "Ampliando", Toast.LENGTH_LONG).show();
                break;
            case R.id.itemMapa:
                //mostrarMapa(posicion);
                Toast.makeText(getApplicationContext(), "Mostrando mapa", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }



}
