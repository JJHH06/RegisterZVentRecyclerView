package com.example.registerzvent.views.addroles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.GuestRoleDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class AddrolesViewModelFactory(private val dataSource: GuestRoleDatabaseDao,
                               private val application: Application
                               ):ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddrolesViewModel::class.java)){
            return AddrolesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}