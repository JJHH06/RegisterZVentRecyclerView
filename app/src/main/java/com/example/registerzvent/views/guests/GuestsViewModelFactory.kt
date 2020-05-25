package com.example.registerzvent.views.guests

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource


/**
 * ViewModelFactory de Guests
 *
 * Jose Hurtarte 19707
 */
class GuestsViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                             private val application: Application): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuestsViewModel::class.java)){
            return GuestsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}