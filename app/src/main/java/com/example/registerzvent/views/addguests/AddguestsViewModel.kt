package com.example.registerzvent.views.addguests

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import kotlinx.coroutines.*

class AddguestsViewModel(
    val database: GuestRoleDatabaseDao,
    application: Application ): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)


    val listadoRoles = database.getAllRoles()
    init {
        Log.i("AddguestsViewModel", "AdguestsViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AddguestsViewModel", "AddguestsViewModel destroyed!")
    }

    private suspend fun insert(guest: Guest){
        withContext(Dispatchers.IO){
            database.insertGuest(guest)
        }
    }

    fun addGuest(name: String, email: String, phone: String, roleId: Long){
        uiScope.launch{
            insert(Guest(name = name,email = email,phone = phone,roleOfGuestId = roleId))
        }
    }
}