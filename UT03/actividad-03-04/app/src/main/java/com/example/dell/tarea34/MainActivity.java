package com.example.dell.tarea34;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etOperando1, etOperando2;
    TextView tvResultado;
    Button btnSumar, btnRestar, btnMultiplicar, btnDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOperando1 = (EditText) findViewById(R.id.etOperando1);
        etOperando2 = (EditText) findViewById(R.id.etOperando2);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnRestar = (Button) findViewById(R.id.btnRestar);
        btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        btnDividir = (Button) findViewById(R.id.btnDividir);

        btnSumar.setOnClickListener(new ManejadorEventoClicBoton());
        btnRestar.setOnClickListener(new ManejadorEventoClicBoton());
        btnMultiplicar.setOnClickListener(new ManejadorEventoClicBoton());
        btnDividir.setOnClickListener(new ManejadorEventoClicBoton());
    }



    private class ManejadorEventoClicBoton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int identificadorBoton = view.getId();
            double num1, num2;
            switch (identificadorBoton) {
                case R.id.btnSumar:
                    try {
                        num1 = Double.parseDouble(etOperando1.getText().toString());
                        num2 = Double.parseDouble(etOperando2.getText().toString());
                        tvResultado.setText("" + (num1 + num2));
                    } catch (NumberFormatException e) {
                        logException("Error en el parseo", e);
                        tvResultado.setText("Algún operador sin número" );
                    }
                    break;
                case R.id.btnRestar:
                    try {
                        num1 = Double.parseDouble(etOperando1.getText().toString());
                        num2 = Double.parseDouble(etOperando2.getText().toString());
                        tvResultado.setText("" + (num1 - num2));
                    } catch (NumberFormatException e) {
                        logException("Error en el parseo", e);
                        tvResultado.setText("Algún operador sin número");
                    }
                    break;
                case R.id.btnMultiplicar:
                    try {
                        num1 = Double.parseDouble(etOperando1.getText().toString());
                        num2 = Double.parseDouble(etOperando2.getText().toString());
                        tvResultado.setText("" + (num1 * num2));
                    } catch (NumberFormatException e) {
                        logException("Error en el parseo", e);
                        tvResultado.setText("Algún operador sin número");
                    }
                    break;
                case R.id.btnDividir:
                    try {
                        num1 = Double.parseDouble(etOperando1.getText().toString());
                        num2 = Double.parseDouble(etOperando2.getText().toString());
                        tvResultado.setText("" + (num1 / num2));
                    } catch (NumberFormatException e) {
                        logException("Error en el parseo", e);
                        tvResultado.setText("Algún operador sin número");
                    } catch (ArithmeticException e) { //Este bloque no es necesario ya que en realidad no se provoca excepoión porque Android ya la trata dando como resultado "Infinity"
                        logException("Error en la operación", e);
                        tvResultado.setText("No se puede dividir entre 0");
                    }

            }
        }
    }

    private void logException(String msg, Throwable e) {
        Log.e(getApplicationContext().getPackageName(),msg, e);
    }
}

