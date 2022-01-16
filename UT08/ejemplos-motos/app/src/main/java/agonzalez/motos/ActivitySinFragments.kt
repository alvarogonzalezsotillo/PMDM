package agonzalez.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton

class ActivitySinFragments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_fragments)

        findViewById<AppCompatImageButton>(R.id.imgb1).setOnClickListener{
            showItem(0);
        }
        findViewById<AppCompatImageButton>(R.id.imgb2).setOnClickListener{
            showItem(1);
        }
        findViewById<AppCompatImageButton>(R.id.imgb3).setOnClickListener{
            showItem(2);
        }
    }

    private fun showItem(i: Int) {
        val intent = Intent(this, DetailView::class.java)
        intent.putExtra("modelo",i)
        startActivity(intent)
    }
}