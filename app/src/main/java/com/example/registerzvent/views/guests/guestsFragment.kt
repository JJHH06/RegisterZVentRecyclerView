package com.example.registerzvent.views.guests

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.example.registerzvent.R

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.GuestRoleDatabaseDao

import com.example.registerzvent.databinding.FragmentGuestsBinding
import com.example.registerzvent.views.activity.MainActivity

//Jose Hurtarte 19707

/**
 * Clase de la vista de el despliegue de invitados
 */
class guestsFragment : Fragment() {
    private lateinit var viewModel: GuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory
    private lateinit var binding: FragmentGuestsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentGuestsBinding>(
            inflater,
            R.layout.fragment_guests, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = GuestsViewModelFactory(dataSource,application)


        viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(GuestsViewModel::class.java)

        binding.guestsViewModel = viewModel

        binding.lifecycleOwner = this //Hace que se actualice el databinding cuando es utilizado


        /**
         * Floating action Button utilizado para navegar
         */
        binding.floatingActionButtonAdd.setOnClickListener {view : View ->
            view.findNavController()
                .navigate(guestsFragmentDirections.actionGuestsFragmentToAddguestsFragment())
        }

        return binding.root
    }


}