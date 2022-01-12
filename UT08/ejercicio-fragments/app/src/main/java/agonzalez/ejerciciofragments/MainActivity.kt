package agonzalez.ejerciciofragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "PRUEBAFRAGMENTS"
    }

    private fun button1() = findViewById<Button>(R.id.button1)
    private fun button2() = findViewById<Button>(R.id.button2)
    private fun button3() = findViewById<Button>(R.id.button3)

    private val fragment1 = PrimerFragment()
    private val fragment2 = SegundoFragment()
    private val fragment3 = TercerFragment()

    private fun setFragment(f: Fragment) : Unit {
        Log.d(TAG,"Poniendo fragmento:${f}")
        val fragmentManager = this.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,f)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = this.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer,fragment2)
        transaction.commit()


        button1().setOnClickListener{
            setFragment(fragment1)
        }
        button2().setOnClickListener{
            setFragment(fragment2)
        }
        button3().setOnClickListener{
            setFragment(fragment3)
        }

    }


}