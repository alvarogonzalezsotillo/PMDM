package alvarogonzalez.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.builtInButton).setOnClickListener( (View v)-> {
            startActivity(new Intent(this, ConexionBuiltInActivity.class));
        });

        findViewById(R.id.volleyButton).setOnClickListener( (View v)-> {
            startActivity(new Intent(this, ConexionVolleyActivity.class));
        });

        findViewById(R.id.retrofitConListaButton).setOnClickListener( (View v)-> {
            startActivity(new Intent(this, ConexionRetrofitActivity.class));
        });

        findViewById(R.id.retrofitConPathButton).setOnClickListener( (View v)-> {
            startActivity(new Intent(this, ConexionRetrofitConPathActivity.class));
        });

    }
}