package agonzalez.ejerciciofragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 * Use the [PrimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrimerFragment : Fragment() {

    companion object{
        const val TAG = "PRIMERFRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_primer, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Destruyen el fragmento 1")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Pausan el fragmento 1")
    }

}