package com.example.registerzvent.views.registry

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.registerzvent.R
import com.example.registerzvent.databinding.FragmentRegistryBinding
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.views.activity.MainActivity

/**
 * Clase de el fragmento de registro
 *
 * Jose Hurtarte 19707
 */
class registryFragment : Fragment() {   
    private lateinit var viewModel: RegistryViewModel
    private lateinit var viewModelFactory: RegistryViewModelFactory
    private lateinit var binding:FragmentRegistryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

         binding = DataBindingUtil.inflate<FragmentRegistryBinding>(inflater,
             R.layout.fragment_registry,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao


        viewModelFactory = RegistryViewModelFactory(dataSource,application)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RegistryViewModel::class.java)

        binding.registryViewModel = viewModel

        binding.lifecycleOwner = this //Hace que se actualice el databinding cuando es utilizado


        setHasOptionsMenu(true)



        //Este es el observador del LiveData de el contador para cuando se cambie de casilla
        viewModel.contador.observe(viewLifecycleOwner, Observer {/*newContador->*/
                viewModel.progressInList()
        })


        viewModel.invitadoActual.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity).supportActionBar?.title = viewModel.titleOfActionBar()
        })

        viewModel.finish.observe(viewLifecycleOwner, Observer {
            if(viewModel.finish.value == true) {
                if(viewModel.listadoNoExiste()){
                    Toast.makeText(context, "Porfavor ingrese por lo menos UN INVITADO", Toast.LENGTH_LONG)
                        .show()
                }
                moveToActivity()
            }
        })



        return binding.root
    }


    /**
     * Esta funcion hace los calculos para mandar como
     * parametros al otro fragment, y al finalizarlos
     * inicia el nuevo fragment y manda los parametros
     */
    private fun moveToActivity(){

        //La funcion que prepara los datos que se mandan al fragment de resultados
       viewModel.finalToResults()

        val action = registryFragmentDirections.actionRegistryFragmentToResultsFragment()

            action.invitedSummary = viewModel.finalResultMessage()
            action.registeredPeople = viewModel.finalNumberOfRegisteredGuests()
            action.invitedPeople = viewModel.numberOfInvitedPeople()

        //llamado al otro fragment
        view?.findNavController()?.navigate(action)
    }


    /**
     * Crea el registry menu, causando a[i que ya se puedan ver los botones
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.registry_menu, menu)

    }


    /**
     * Funciona como tipo click listener, solo que dependiendo del boton seleccionado
     * hace una accion distinta, la cual se decide con el when
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.guest_accepted -> {
                viewModel.confirmedRegisterGuest()

            }

            R.id.guest_denied -> {
                viewModel.deniedRegisterGuest()

            }
        }
        return super.onOptionsItemSelected(item)
    }


}
