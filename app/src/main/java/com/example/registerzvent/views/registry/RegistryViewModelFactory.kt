package com.example.registerzvent.views.registry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException

/**
 * Factory para el registro
 *
 * Jose Hurtarte 19707
 */
class RegistryViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                               private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistryViewModel::class.java)){
            return RegistryViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}