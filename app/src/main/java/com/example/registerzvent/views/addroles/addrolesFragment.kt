package com.example.registerzvent.views.addroles

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.registerzvent.R
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.databinding.FragmentAddrolesBinding

/**
 * A simple [Fragment] subclass.
 */
class addrolesFragment : Fragment() {
    private lateinit var viewModel: AddrolesViewModel
    private lateinit var viewModelFactory: AddrolesViewModelFactory
    private lateinit var binding: FragmentAddrolesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddrolesBinding>(
            inflater,
            R.layout.fragment_addroles,container,false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = AddrolesViewModelFactory(dataSource,application)

        viewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(AddrolesViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner //Por si se decide usar livedata
        setHasOptionsMenu(true)

        //operaciones iniciales aqui

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.save_guest -> {
                if(binding.textFieldRoleName.text.isNullOrEmpty()|| binding.roleDescriptionTextfield.text.isNullOrEmpty()){
                    Toast.makeText(context,"Porfavor llene los campos vacios",Toast.LENGTH_LONG).show()
                }
                else{
                    //AQUI SE LLENA LA BASE DE DATOS DEL RON

                    viewModel.addRole(
                        binding.textFieldRoleName.text.toString(),
                        binding.roleDescriptionTextfield.text.toString(),
                        binding.seekbarRoleOrder.progress+1
                    )
                    view?.findNavController()
                        ?.navigate(addrolesFragmentDirections.actionAddrolesFragmentToRolesFragment())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
