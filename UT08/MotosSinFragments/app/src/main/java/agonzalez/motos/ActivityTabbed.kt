package agonzalez.motos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import agonzalez.motos.fragments.HarleyFragment

import agonzalez.motos.fragments.BultacoFragment
import agonzalez.motos.fragments.GuzzyFragment


import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager
import java.lang.IllegalArgumentException


//En este ejercicio, modificaremos el ejercicio DashboardMotos5 incorporando un sistema de pestañas o tabs

// de forma que se puedan cargar una serie de fragments en la misma actividad mediante un sistema de

// navegación por pestañas. Además mantendremos la funcionalidad que ofrecía el ejercicio anterior (sistema

// basado en swipe views, lo cual consiste en que cambiaremos de vista deslizado el dedo de derecha a izquierda  para cargar el

// fragmento siguiente y de izquierda a derecha para cargar el fragmento anterior. Es decir, movimientos

//horizontales. Por ello en el layout activity_main.xml mantendremos la etiqueta de tipo ViewPager, pero además,

// por encima de ella incorporaremos otras etiquetas adicionales que explicamos en dicho fichero
class ActivityTabbed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)


        /* Para la función de paginación mediante swipe view */
        val myPagerAdapter = MyPagerAdapter(supportFragmentManager)

        //Referenciamos el ViewPager que hemos puesto de layout
        val viewPager = findViewById<View>(R.id.paginador) as ViewPager
        viewPager.adapter = myPagerAdapter


        /* Para la función de navegación mediante pestañas */
        val tabLayout = findViewById<View>(R.id.pestanias) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

}


class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {



    override fun getItem(i: Int): Fragment {
        val fragment: Fragment?
        when (i) {
            0 -> fragment = BultacoFragment() //Este es el que cargará por defecto (el 0)
            1 -> fragment = HarleyFragment()
            2 -> fragment = GuzzyFragment()
            else -> throw IllegalArgumentException("$i")
        }
        return fragment
    }


    override fun getCount(): Int {
        return 3 //Indicamos tantos como posibles fragentos
    }


    //Añadimos sobre el ejercicio anterior la sobreescritura de getPageTitle

    //Añadimos sobre el ejercicio anterior la sobreescritura de getPageTitle
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Bultaco"
            1 -> return "Harley Davidson"
            2 -> return "Moto Guzzi"
        }
        throw IllegalArgumentException("$position")
    }
}