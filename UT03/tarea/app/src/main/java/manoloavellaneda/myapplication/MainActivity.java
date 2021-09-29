package manoloavellaneda.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Creación de los controles
    private EditText et1, et2;
    private TextView tvResultado;
    private CheckBox chkb1, chkb2, chkb3, chkb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciación de los controles
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        tvResultado=(TextView)findViewById(R.id.tvResultado);
        chkb1=(CheckBox)findViewById(R.id.chkb1);
        chkb2=(CheckBox)findViewById(R.id.chkb2);
        chkb3=(CheckBox)findViewById(R.id.chkb3);
        chkb4=(CheckBox)findViewById(R.id.chkb4);
    }

    //Método que se ejecutará al pulsar sobre un CheckBox
    public void operar(View view){

        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        try {
            int n1 = Integer.parseInt(valor1);
            int n2 = Integer.parseInt(valor2);

            String resultado="";
            if(chkb1.isChecked()){
                int suma=n1+n2;
                resultado="La suma es: " + suma;
            }
            if(chkb2.isChecked()){
                int resta=n1-n2;
                resultado+="\nLa resta es: " + resta;
            }
            if(chkb3.isChecked()){
                int producto=n1*n2;
                resultado+="\nEl producto es: " + producto;
            }
            if(chkb4.isChecked()){
                int division=n1/n2;
                resultado+="\nLa división es: " + division;
            }

            tvResultado.setText(resultado);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Falta operando", Toast.LENGTH_LONG).show();
            //Como en activity_main.xml he forzado que la introducción de operandos sea numérica
            //solamente podrá producirse excepción debida a falta de algún operador
        }


    }

}
