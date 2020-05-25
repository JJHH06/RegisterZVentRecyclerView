package com.example.registerzvent.views.guests

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.database.GuestWithRole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * ViewModel de GuestFragment
 *
 * Jose Hurtarte 19707
 */
class GuestsViewModel(
    val database: GuestRoleDatabaseDao,
    application: Application ) : AndroidViewModel(application) {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)

    val listaDeInvitados =  database.getAllGuests()

    val listaDeInvitadosString = Transformations.map(listaDeInvitados){
        formatGuests(it)
    }




    init {

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("GuestsViewModel", "GuestsViewModel destroyed!")
    }


    fun formatGuests(listado: List<GuestWithRole>): String{
        var resultado = ""
        if(listado.isEmpty()){
            resultado = "No hay invitados al evento"
        }
        else{
            for(guest in listado){
                resultado += guest.toString()+"\n\n\n"
            }
        }
        return resultado

    }

}