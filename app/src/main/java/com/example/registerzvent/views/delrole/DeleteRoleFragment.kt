package com.example.registerzvent.views.delrole

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.registerzvent.R
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.databinding.DeleteRoleFragmentBinding

class DeleteRoleFragment : Fragment() {
    private lateinit var viewModel: DeleteRoleViewModel
    private lateinit var viewModelFactory: DeleteRoleViewModelFactory
    private lateinit var binding: DeleteRoleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<DeleteRoleFragmentBinding>(
            inflater,
            R.layout.delete_role_fragment,container,false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = DeleteRoleViewModelFactory(dataSource, application, DeleteRoleFragmentArgs.fromBundle(requireArguments()).idRecyclerGuest)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DeleteRoleViewModel::class.java)

        binding.deleteRoleViewModel = viewModel

        binding.lifecycleOwner = this

        //Toast.makeText(context, "${DeleteRoleFragmentArgs.fromBundle(requireArguments()).idRecyclerGuest}", Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)

        return binding.root
    }
    /**
     * Crea el addguest menu
     *
     * Hace un inflate de el menu con el logo de guardar
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.delete_menu, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.delete_item -> {

                viewModel.deleteSelectedRole()
                view?.findNavController()?.navigate(DeleteRoleFragmentDirections.actionDeleteRoleFragmentToRolesFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
