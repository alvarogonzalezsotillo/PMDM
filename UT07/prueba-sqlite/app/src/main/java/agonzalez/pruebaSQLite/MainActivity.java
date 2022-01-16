package agonzalez.pruebaSQLite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOGPRUEBASQLITE";
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

    private Button anteriorButton(){ return findViewById(R.id.anteriorButton); }
    private Button posteriorButton(){ return findViewById(R.id.posteriorButton); }
    private Button nuevoButton(){ return findViewById(R.id.nuevoButton); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        id = 3;

        while( cursor.moveToNext() ){
            final var s = cursor.getString(2);
            Log.d(TAG, "En la query nombre:" + s );
        }
    }
}
