package com.example.registerzvent.views.delguest

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException

class DeleteGuestViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                                  private val application: Application,
                                  private val idOfGuest: Long): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeleteGuestViewModel::class.java)) {
            return DeleteGuestViewModel(dataSource, application, idOfGuest) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}