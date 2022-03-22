package agonzalez.motos

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import agonzalez.motos.databinding.ActivityNavigationDrawerBinding
import agonzalez.motos.fragments.BultacoFragment
import agonzalez.motos.fragments.GuzzyFragment
import agonzalez.motos.fragments.HarleyFragment
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat

import android.view.View


class ActivityNavigationDrawer : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarActivityNavigationDrawer.toolbar)

        binding.appBarActivityNavigationDrawer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController =
            findNavController(R.id.nav_host_fragment_content_activity_navigation_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration( setOf( ), drawerLayout )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        // https://stackoverflow.com/questions/58400886/the-icons-in-navigationview-are-not-displayed
        navView.itemIconTintList = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_navigation_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        val fragment : Fragment? = when(id){
            R.id.action_bultaco -> BultacoFragment()
            R.id.action_harley -> HarleyFragment()
            R.id.action_guzzy -> GuzzyFragment()
            else -> {
                openDrawer()
                null
            }
        }

        if( fragment != null ){
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, fragment).commit();
            return true
        }

        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_activity_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.e(TAG, "Seleccionado $item")
        val id = item.itemId

        val fragment : Fragment? = when(id){
            R.id.itemBultaco -> BultacoFragment()
            R.id.itemHarley -> HarleyFragment()
            R.id.itemMotoGuzzi -> GuzzyFragment()
            else -> null
        }

        if( fragment != null ){
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, fragment).commit();
            closeDrawer()
            return true
        }
        return false
    }

    private fun closeDrawer() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun openDrawer() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.openDrawer(GravityCompat.START)
    }

    companion object{
        val TAG = "MOTOSDRAWER"
    }
}