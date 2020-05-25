package com.example.registerzvent.views.addguests

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.registerzvent.R
import com.example.registerzvent.databinding.FragmentAddguestsBinding
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.Roles
import com.example.registerzvent.views.activity.MainActivity

/**
 * Jose Hurtarte 19707
 * Clase encargada de la vista para agregar Invitados
 */
class addguestsFragment : Fragment() {

    /**
     * Se declaran los viewModel y ViewModelFactory
     *
     * En este caso no sirven de mucho pero se dejan para futuras implementaciones
     */
    private lateinit var viewModel: AddguestsViewModel
    private lateinit var viewModelFactory: AddguestsViewModelFactory

    private lateinit var binding: FragmentAddguestsBinding

    /**
     * Oncreate view de esta vista
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentAddguestsBinding>(
            inflater,
            R.layout.fragment_addguests, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = AddguestsViewModelFactory(dataSource,application)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddguestsViewModel::class.java)

        binding.addGuestViewModel = viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Crea el addguest menu
     *
     * Hace un inflate de el menu con el logo de guardar
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.addguest_menu, menu)

    }

    /**
     * Funciona como tipo click listener, solo que dependiendo del boton seleccionado
     *
     * En general esta solo se encarga de guardar el nuevo invitado
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.save_guest -> {
                if (binding.nameTextField.text.isNullOrEmpty() || binding.phoneTextField.text.isNullOrEmpty()||binding.emailTextField.text.isNullOrEmpty() || binding.spinner.selectedItem == null){
                    if (binding.spinner.selectedItem == null){
                        Toast.makeText(context, "Porfavor ngrese al menos un ROL, antes de ingresar invitados", Toast.LENGTH_LONG)
                            .show()
                    }
                    else {
                        Toast.makeText(context, "Porfavor llene los campos", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                else {
                    //(activity as MainActivity).guestList.add() //Con esto de aca agrego nuevos Guests
                    viewModel.addGuest(
                        binding.nameTextField.text.toString(),
                        binding.emailTextField.text.toString(),
                        binding.phoneTextField.text.toString(),
                        (binding.spinner.selectedItem as Roles).eventRolesId
                    )

                    view?.findNavController()//Navega hacia el fragment de Guests
                        ?.navigate(addguestsFragmentDirections.actionAddguestsFragmentToGuestsFragment())


                }
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
