package agonzalez.pruebaSQLite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PRUEBAPERMISOS";
    private DB db;
    private SQLiteDatabase sqlite;

    static public class DB extends SQLiteOpenHelper {
        static SQLiteDatabase.CursorFactory createFactory() {
            return (db, masterQuery, editTable, query) -> {
                Log.d(TAG, "Creo un cursor para una query:" + query);
                return new SQLiteCursor(masterQuery, editTable, query);
            };
        }

        public DB(@Nullable Context context, @Nullable String name,  int version, @Nullable DatabaseErrorHandler errorHandler) {
            super(context, name, createFactory(), version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table tabla( id integer primary key, nombre varchar(20), valor integer )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.e(TAG, "No está previsto onUpgrade");
            throw new UnsupportedOperationException("DB.onUpgrade");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);


        findViewById(R.id.button).setOnClickListener( (View v)-> pruebaPermisos(false) );
        findViewById(R.id.buttonInsistente).setOnClickListener( (View v)-> pruebaPermisos(true) );
        findViewById(R.id.buttonConfiguracion).setOnClickListener( (View v)-> abreConfiguracion() );

    }

    private void abreConfiguracion() {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//        startActivity(intent);
        String dial = "tel:324124321";
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
    }


    private void pruebaPermisos(boolean insistente){
        var thisActivity = this;
        Log.d(TAG,"Pruebo los permisos con insistencia:" + insistente);

        var permisoQuePido = Manifest.permission.READ_CONTACTS;

        if (ContextCompat.checkSelfPermission(thisActivity, permisoQuePido) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"No tengo el permiso concedido");
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity, permisoQuePido)) {
                Log.d(TAG, "Hay que mostrar la razón por la que se quiere el permiso");

                if( !insistente ) {
                    Log.d(TAG, "No soy insistente");
                    Toast.makeText(this, "Necesito el permiso porque....", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d(TAG, "Soy Insistente");
                    ActivityCompat.requestPermissions(thisActivity, new String[]{permisoQuePido}, 27);
                }
            }
            else {
                Log.d(TAG, "Solicito el permiso");
                ActivityCompat.requestPermissions(thisActivity, new String[]{permisoQuePido}, 27);
                Log.d(TAG, "Solicitando el permiso");
            }
        }
        else{
            Log.d(TAG,"Tengo el permiso concedido");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        Log.d(TAG,"Tenemos el resultado de una petición de permisos");
        switch (requestCode) {
            case 27: {
                Log.d(TAG,"Es la solicitud 27");
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"Tenemos el primer permiso solicitado concedido!!!");
                }
                else {
                    Log.d(TAG,"No nos han concedido el primer permiso solicitado :(");
                }
                return;
            }
            default: {
                Log.d(TAG,"No recuerdo haber pedido permisos");
            }

        }

    }





    private void pruebaSQLite() {
        db = new DB(this, "BD-otro", 1, null );
        sqlite = db.getWritableDatabase();
        final var id = 1;
        final var nombre = "PMDM''";
        final var valor = 3;
        try {
            sqlite.execSQL("insert into tabla(id,nombre,valor) values (?,?,?)", new Object[]{id, nombre, valor});
            Log.d(TAG, "Ejecutado statement");
        }
        catch( Throwable t ){
            Log.d(TAG, "Ha fallado" + t, t );
        }
        var cursor = sqlite.rawQuery("select * from tabla",null);

        while( cursor.moveToNext() ){
            final var s = cursor.getString(2);
            Log.d(TAG, "En la query nombre:" + s );
        }
    }
}
