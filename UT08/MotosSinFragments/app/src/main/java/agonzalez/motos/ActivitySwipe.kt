package agonzalez.motos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

import androidx.viewpager.widget.ViewPager


public class ActivitySwipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        val myPagerAdapter = MyPagerAdapter(supportFragmentManager)

        //Referenciamos el ViewPager que hemos puesto de layout
        val viewPager = findViewById<View>(R.id.paginador) as ViewPager
        viewPager.adapter = myPagerAdapter
    }
}

