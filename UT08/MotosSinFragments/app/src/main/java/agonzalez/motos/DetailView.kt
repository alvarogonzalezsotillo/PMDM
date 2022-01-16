package agonzalez.motos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView


class DetailView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        val tvMarca = findViewById<TextView>(R.id.tvMarca)
        val tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        val imgvLogo = findViewById<ImageView>(R.id.imgvLogo)
        val imgvFoto = findViewById<ImageView>(R.id.imgvFoto)
        val bundle = intent.extras
        val modelo = bundle!!.getInt("modelo")
        assert(modelo in 0..2)
        when (modelo) {
            0 -> {
                imgvLogo!!.setImageResource(R.drawable.logo_bultaco)
                imgvFoto!!.setImageResource(R.drawable.imagen_bultaco)
            }
            1 -> {
                imgvLogo!!.setImageResource(R.drawable.logo_harley_davidson)
                imgvFoto!!.setImageResource(R.drawable.imagen_harley)
            }
            2 -> {
                imgvLogo!!.setImageResource(R.drawable.logo_moto_guzzi)
                imgvFoto!!.setImageResource(R.drawable.imagen_moto_guzzi)
            }
        }
        tvMarca!!.text = resources.getStringArray(R.array.nombre)[modelo]
        tvDescripcion!!.text = resources.getStringArray(R.array.descripcion)[modelo]
    }
}