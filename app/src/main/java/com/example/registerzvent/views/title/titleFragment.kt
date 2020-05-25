package com.example.registerzvent.views.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.registerzvent.R
import com.example.registerzvent.databinding.FragmentTitleBinding
import com.example.registerzvent.views.title.titleFragmentDirections
//Jose Hurtarte 19707
/**
 * Clase del title fragment
 */
class titleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        binding.startButton.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(titleFragmentDirections.actionTitleFragmentToRegistryFragment())
        }
        //Menu de opciones habilitado
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Zvent App"
        return binding.root

    }

    /**
     * Crea el menu a desplegar
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu,menu)
    }

    /**
     * Mediante la opcion seleccionasa, como el los id estaban relacionados
     * nos lleva hacia el ragment asociado
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
