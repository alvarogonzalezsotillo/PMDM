package manoloavellaneda.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Creación de los controles
    private EditText et1, et2;
    private TextView tvResultado;
    private RadioButton rb1, rb2, rb3, rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciación de los controles
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        tvResultado=(TextView)findViewById(R.id.tvResultado);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        rb4=(RadioButton)findViewById(R.id.rb4);
    }

    //Método que se ejecutará al clicar sobre un RadioButton
    public void operar2(View view){
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        int n1=Integer.parseInt(valor1);
        int n2=Integer.parseInt(valor2);
        String resultado="";
        if(rb1.isChecked()){
            int suma=n1+n2;
            resultado="La suma es: " + suma;
        }
        if(rb2.isChecked()){
            int resta=n1-n2;
            resultado="\nLa resta es: " + resta;
        }
        if(rb3.isChecked()){
            int producto=n1*n2;
            resultado="\nEl producto es: " + producto;
        }
        if(rb4.isChecked()){
            int division=n1/n2;
            resultado="\nLa división es: " + division;
        }

        tvResultado.setText(resultado);
    }

    //Método que se ejecutará al pulsar sobre un RadioButton (otra forma)
    public void operar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int n1 = Integer.parseInt(valor1);
        int n2 = Integer.parseInt(valor2);
        String resultado = "";

        switch (view.getId()) {
            case R.id.rb1:
                int suma = n1 + n2;
                resultado = "La suma es: " + suma;
                break;
            case R.id.rb2:
                int resta = n1 - n2;
                resultado = "\nLa resta es: " + resta;
                break;
            case R.id.rb3:
                int producto = n1 * n2;
                resultado = "\nEl producto es: " + producto;
                break;
            case R.id.rb4:
                int division = n1 / n2;
                resultado = "\nLa división es: " + division;
                break;
        }

        tvResultado.setText(resultado);
    }
}
