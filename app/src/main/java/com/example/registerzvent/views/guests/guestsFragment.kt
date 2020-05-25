package com.example.registerzvent.views.guests

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.registerzvent.R

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
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

        val adapter = GuestsAdapter(GuestListener { eventGuestsId ->
           // Toast.makeText(context, "${eventGuestsId}", Toast.LENGTH_LONG).show()
            moveToActivity(eventGuestsId)
        })
        binding.guestsList.adapter = adapter

        viewModel.listaDeInvitados.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        /**
         * Floating action Button utilizado para navegar
         */
        binding.floatingActionButtonAdd.setOnClickListener {view : View ->
            view.findNavController()
                .navigate(guestsFragmentDirections.actionGuestsFragmentToAddguestsFragment())
        }

        return binding.root
    }
    private fun moveToActivity(id: Long){
        val action = guestsFragmentDirections.actionGuestsFragmentToDeleteGuestFragment()
        action.idRecyclerNewGuest = id
        view?.findNavController()?.navigate(action)
    }


}
