package com.example.registerzvent.views.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.registerzvent.R
import com.example.registerzvent.databinding.FragmentAboutBinding
//Jose Hurtarte 19707
/**
 * About App class, sirve para colocar informacion acerca de la app
 * no es tan complicada ya que esta no tiene alguna forma definida de
 * hacer navigation, por eso se queda casi default
 */
class aboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater,
            R.layout.fragment_about, container, false
        )
        (activity as AppCompatActivity).supportActionBar?.title = "Acerca de"
        return binding.root
    }
}
