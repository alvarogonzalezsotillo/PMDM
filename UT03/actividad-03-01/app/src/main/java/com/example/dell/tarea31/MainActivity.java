package com.example.dell.tarea31;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etOperando1, etOperando2;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOperando1=(EditText)findViewById(R.id.etOperando1);
        etOperando2=(EditText)findViewById(R.id.etOperando2);
        tvResultado=(TextView)findViewById(R.id.tvResultado);
    }

    public void suma(View v){
        int num1, num2;

        try{
            num1=Integer.parseInt(etOperando1.getText().toString());
            num2=Integer.parseInt(etOperando2.getText().toString());
            tvResultado.setText(""+(num1+num2));
        }catch(NumberFormatException e){
            tvResultado.setText("Los números deben ser enteros");
        }
    }


}
