package com.example.registerzvent.views.addguests

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class AddguestsViewModelFactory(private  val dataSource: GuestRoleDatabaseDao,
                                private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddguestsViewModel::class.java)){
            return AddguestsViewModel(dataSource,application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}