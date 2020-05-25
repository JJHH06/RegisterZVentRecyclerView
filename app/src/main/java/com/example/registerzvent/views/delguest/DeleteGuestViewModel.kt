package com.example.registerzvent.views.delguest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import kotlinx.coroutines.*

class DeleteGuestViewModel(val database: GuestRoleDatabaseDao,
                           application: Application,
                           guestId: Long) : AndroidViewModel(application){
    private var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main +viewModelJob)

    val _selectedGuest = MutableLiveData<Guest>(Guest())
    val selectedGuest: LiveData<Guest>
        get() = _selectedGuest


    var idRespaldo =  1L

    init{
        idRespaldo = guestId
        uiScope.launch {
            _selectedGuest.value = getSelectedGuest(idRespaldo)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun deleteSelectedGuest(){
        uiScope.launch {
            getSelectedGuest(idRespaldo)?.let { deleteGuest(it) }
        }

    }

    private suspend fun getSelectedGuest(id: Long): Guest?{
        return withContext(Dispatchers.IO){
            database.getGuest(id)
        }
    }

    private suspend fun deleteGuest(guest: Guest){
        withContext(Dispatchers.IO){
            database.deleteGuest(guest)
        }
    }
}