package com.example.registerzvent.views.delrole

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException

class DeleteRoleViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                                 private val application: Application,
                                 private val idOfRole: Long):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DeleteRoleViewModel::class.java)){
            return DeleteRoleViewModel(dataSource, application, idOfRole) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
