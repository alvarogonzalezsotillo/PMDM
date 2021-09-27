package com.example.dell.tarea23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1, num2;

                try{
                    num1=Double.parseDouble(etOperando1.getText().toString());
                    num2=Double.parseDouble(etOperando2.getText().toString());
                    tvResultado.setText(""+(num1+num2));
                }catch(NumberFormatException e){
                    tvResultado.setText("Algún operador sin número");
                }
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1, num2;

                try{
                    num1=Double.parseDouble(etOperando1.getText().toString());
                    num2=Double.parseDouble(etOperando2.getText().toString());
                    tvResultado.setText(""+(num1-num2));
                }catch(NumberFormatException e){
                    tvResultado.setText("Algún operador sin número");
                }
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1, num2;

                try{
                    num1=Double.parseDouble(etOperando1.getText().toString());
                    num2=Double.parseDouble(etOperando2.getText().toString());
                    tvResultado.setText(""+(num1*num2));
                }catch(NumberFormatException e){
                    tvResultado.setText("Algún operador sin número");
                }
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1, num2;

                try{
                    num1=Double.parseDouble(etOperando1.getText().toString());
                    num2=Double.parseDouble(etOperando2.getText().toString());
                    tvResultado.setText(""+(num1/num2));
                }catch(NumberFormatException e){
                    tvResultado.setText("Algún operador sin número");
                }
                catch (ArithmeticException e){ //Este bloque no es necesario ya que en realidad no se provoca excepoión porque Android ya la trata dando como resultado "Infinity"
                    tvResultado.setText("No se puede dividir entre 0");
                }
            }
        });
    }
}
