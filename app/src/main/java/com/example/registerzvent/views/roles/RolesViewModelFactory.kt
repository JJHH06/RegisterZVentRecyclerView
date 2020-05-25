package com.example.registerzvent.views.roles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException

class RolesViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                            private val application: Application
                            ):ViewModelProvider.Factory {


    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RolesViewModel::class.java)){
            return RolesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}