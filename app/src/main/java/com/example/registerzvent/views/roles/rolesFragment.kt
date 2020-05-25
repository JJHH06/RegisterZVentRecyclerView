package com.example.registerzvent.views.roles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.registerzvent.R
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.databinding.FragmentRolesBinding

/**
 * A simple [Fragment] subclass.
 */
class rolesFragment : Fragment() {
    private lateinit var viewModel: RolesViewModel
    private lateinit var viewModelFactory: RolesViewModelFactory
    private lateinit var binding: FragmentRolesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentRolesBinding>(
            inflater,
            R.layout.fragment_roles,container,false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GuestRoleDatabase.getInstance(application).guestRoleDatabaseDao

        viewModelFactory = RolesViewModelFactory(dataSource, application)


        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RolesViewModel::class.java)

        binding.rolesViewModel = viewModel

        binding.lifecycleOwner = this

        binding.floatingActionButtonAdd.setOnClickListener {view : View ->

            view.findNavController()
                .navigate(rolesFragmentDirections.actionRolesFragmentToAddrolesFragment())

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}
