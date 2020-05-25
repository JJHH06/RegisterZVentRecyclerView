package com.example.registerzvent.views.delguest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.registerzvent.R
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.databinding.FragmentDeleteGuestBinding

/**
 * A simple [Fragment] subclass.
 */
class DeleteGuestFragment : Fragment() {
    private lateinit var viewModel: DeleteGuestViewModel
    private lateinit var viewModelFactory: DeleteGuestViewModelFactory
    private lateinit var binding: FragmentDeleteGuestBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDeleteGuestBinding>(
            inflater,
            R.layout.fragment_delete_guest, container,false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = DeleteGuestViewModelFactory(dataSource, application, DeleteGuestFragmentArgs.fromBundle(requireArguments()).idRecyclerNewGuest)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DeleteGuestViewModel::class.java)

        binding.deleteGuestViewModel = viewModel

        binding.lifecycleOwner = this

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

                viewModel.deleteSelectedGuest()
                view?.findNavController()?.navigate(DeleteGuestFragmentDirections.actionDeleteGuestFragmentToGuestsFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
