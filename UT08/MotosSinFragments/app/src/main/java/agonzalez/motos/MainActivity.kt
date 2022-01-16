package agonzalez.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sinFragmentsSutton).setOnClickListener{
            startActivity( Intent(this, ActivitySinFragments::class.java) )
        }
        findViewById<Button>(R.id.swipeButton).setOnClickListener{
            startActivity( Intent(this, ActivitySwipe::class.java) )
        }
        findViewById<Button>(R.id.tabsButton).setOnClickListener{
            startActivity( Intent(this, ActivityTabbed::class.java) )
        }
    }
}